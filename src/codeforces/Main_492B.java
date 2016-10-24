package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_492B {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] in = reader.readLine().split(" ");
		int n = Integer.parseInt(in[0]);
		int l = Integer.parseInt(in[1]);
		int[] array = readArray(n, new StreamTokenizer(reader));

		Arrays.sort(array);

		double maxDistance = 0;
		for (int i = 1; i < n; i++) {
			maxDistance = Math.max(maxDistance, ((double) (array[i] - array[i-1])) / 2);
		}
		maxDistance = Math.max(maxDistance, array[0]);

		maxDistance = Math.max(maxDistance, l - array[n-1]);

		System.out.println(maxDistance);
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
