package utils;

import java.io.IOException;
import java.io.StreamTokenizer;

public class StreamUtils {

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

	public static int[][] readSquareMatrix(int size, StreamTokenizer tokenizer) throws IOException {
		int[][] matrix = new int[size][];
		for (int i = 0; i < size; i++) {
			matrix[i] = readArray(size, tokenizer);
		}
		return matrix;
	}
}
