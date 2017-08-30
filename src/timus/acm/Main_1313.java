package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * К вопросу о спорте
 * http://acm.timus.ru/problem.aspx?space=1&num=1313
 */
public class Main_1313 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.valueOf(reader.readLine());

		int[][] matrix = readSquareMatrix(size, new StreamTokenizer(reader));

		for (int i = 0; i < size; i++) {
			for (int j = i; j >= 0; j--) {
				System.out.print(matrix[j][i - j] + " ");
			}
		}
		for (int i = 1; i < size; i++) {
			for (int j = size - 1; j >= i; j--) {
				System.out.print(matrix[j][size - j + i - 1] + " ");
			}
		}
	}

	private static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static int[][] readSquareMatrix(int size, StreamTokenizer tokenizer) throws IOException {
		int[][] matrix = new int[size][];
		for (int i = 0; i < size; i++) {
			matrix[i] = new int[size];
			for (int j = 0; j < size; j++) {
				matrix[i][j] = readInt(tokenizer);
			}
		}
		return matrix;
	}

}