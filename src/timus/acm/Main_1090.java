package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1090 {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

		int n = readInt(tokenizer);
		int k = readInt(tokenizer);

		int maxSwaps = 0;
		int maxSwapsRow = 0;
		for (int i = 0; i < k; i++) {
			int[] row = readArray(n, tokenizer);
			int swaps = sort(row, 0, n - 1);
			if (swaps > maxSwaps) {
				maxSwaps = swaps;
				maxSwapsRow = i;
			}
		}

		System.out.println(maxSwapsRow + 1);
	}

	private static int sort(int[] row, int left, int right) {
		if (left == right) {
			return 0;
		}

		int res = 0;
		int middle = (left + right) / 2;
		res += sort(row, left, middle);
		res += sort(row, middle + 1, right);

		int leftIndex = left;
		int rightIndex = middle + 1;

		int[] sorted = new int[right - left + 1];
		int sortedIndex = 0;

		while (leftIndex <= middle && rightIndex <= right) {
			if (row[leftIndex] < row[rightIndex]) {
				sorted[sortedIndex] = row[leftIndex];
				leftIndex++;
			} else {
				sorted[sortedIndex] = row[rightIndex];
				res += middle - leftIndex + 1;
				rightIndex++;
			}
			sortedIndex++;
		}
		while(leftIndex <= middle ) {
			sorted[sortedIndex] = row[leftIndex];
			leftIndex++;
			sortedIndex++;

		}
		while(rightIndex <= right ) {
			sorted[sortedIndex] = row[rightIndex];
			rightIndex++;
			sortedIndex++;
		}

		System.arraycopy(sorted, 0, row, left, right - left + 1);

		return res;
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	public static int[] readArray(int size, StreamTokenizer tokenizer) throws IOException {
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = readInt(tokenizer);
		}
		return result;
	}
}
