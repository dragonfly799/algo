package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Куча камней
 * http://acm.timus.ru/problem.aspx?space=1&num=1005
 */
public class Main_1005_2 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(reader.readLine());

		int[] stones = readArray(count, new StreamTokenizer(reader));

		int minDiff = new Diff(stones).minDiff(0, 0, 0);

		System.out.println(minDiff);
	}

	private static int readInt(StreamTokenizer tokenizer) throws IOException {
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

	private static class Diff {
		int[] array;
		int fullSum;

		private Diff(int[] array) {
			this.array = array;
			this.fullSum = 0;
			for (int el : array) {
				fullSum += el;
			}
		}

		private int minDiff(int current, int firstPileSum, int firstPileSize) {
			if (current == array.length || firstPileSize * 2 > array.length) {
				return Math.abs(firstPileSum - (fullSum - firstPileSum));
			} else {
				return Math.min(
						minDiff(current + 1, firstPileSum, firstPileSize),
						minDiff(current + 1, firstPileSum + array[current], firstPileSize + 1)
				);
			}
		}
	}
}
