package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_546B {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(tokenizer);
		int[] badges = readArray(n, tokenizer);
		Arrays.sort(badges);

		int coins = 0;
		for (int i = 1; i < n; i++) {
			if (badges[i] <= badges[i-1]) {
				int newValue = badges[i - 1] + 1;
				coins += newValue - badges[i];
				badges[i] = badges[i-1] + 1;
			}
		}
		System.out.println(coins);
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
