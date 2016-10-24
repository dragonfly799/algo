package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_474B {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int pilesCount = readInt(tokenizer);
		int[] wormsInPilesSums = new int[pilesCount];
		int sum = 0;
		for (int i = 0; i < pilesCount; i++) {
			int read = readInt(tokenizer);
			wormsInPilesSums[i] = read + sum;
			sum += read;
		}

		int wormsCount = readInt(tokenizer);
		for (int i = 0; i < wormsCount; i++) {
			int num = readInt(tokenizer);
			System.out.println(search(wormsInPilesSums, num) + 1);
		}
	}

	private static int search(int[] array, int num) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int i = (left + right) / 2;
			if (array[i] == num) {
				return i;
			}
			if (array[i] > num) {
				if (i == 0 || array[i - 1] < num) {
					return i;
				}
				right = i - 1;
			} else {
				left = i + 1;
			}
		}
		return -1;
	}

	private static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

}
