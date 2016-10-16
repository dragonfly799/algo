package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Отель
 * http://acm.timus.ru/problem.aspx?space=1&num=1319
 */
public class Main_1319 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.valueOf(reader.readLine());
		int[][] matrix = new int[size][size];

		int cell = 0;
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size - i; j++) {
				matrix[j][i + j] = ++cell;
			}
		}
		for (int i = 1; i < size; i++) {
			for (int j = i; j < size; j++) {
				matrix[j][j - i] = ++cell;
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}