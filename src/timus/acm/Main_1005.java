package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Куча камней
 * http://acm.timus.ru/problem.aspx?space=1&num=1005
 */
public class Main_1005 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(reader.readLine());

		int[] stones = readArray(count, new StreamTokenizer(reader));

		int fullSum = 0;
		for (int i = 0; i < count; i++) {
			fullSum += stones[i];
		}

		int minDiff = fullSum;
		for (int i = 0; i < 1 << count - 1; i++) {
			minDiff = Math.min(minDiff, diff(stones, i, fullSum));
		}

		System.out.println(minDiff);
	}

	private static int diff(int[] array, int bitmask, int fullSum) {
		int sum = 0;
		for (int i = 0; i < array.length - 1; i++) {
			if ((bitmask & (1 << i)) != 0) {
				sum += array[i];
			}
		}
		return Math.abs(fullSum - sum * 2);
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

}
