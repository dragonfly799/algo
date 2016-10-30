package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_149A {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int k = readInt(tokenizer);
		int[] months = readArray(12, tokenizer);
		if (k == 0) {
			System.out.println(0);
			return;
		}

		Arrays.sort(months);
		int sum = 0;
		for (int i = 11; i >= 0; i--) {
			sum += months[i];
			if (sum >= k) {
				System.out.println(12 - i);
				return;
			}
		}
		System.out.println(-1);
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
