package spoj;

import java.io.*;

public class Main_BSearch1 {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));

		int arraySize = readInt(tokenizer);
		int queriesCount = readInt(tokenizer);

		int[] array = readArray(arraySize, tokenizer);

		for (int i = 0; i < queriesCount; i++) {
			int num = readInt(tokenizer);
			writer.println(position(array, num));
		}
		writer.flush();
	}

	private static int position(int[] array, int target) {
		int leftBound = 0;
		int rightBound = array.length - 1;

		while (leftBound <= rightBound) {
			int i = (leftBound + rightBound) / 2;

			if (array[i] == target && (i == 0 || array[i - 1] != target)) {
				return i;
			}

			if (array[i] >= target) {
				rightBound = i - 1;
			} else {
				leftBound = i + 1;
			}
		}
		return -1;
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
