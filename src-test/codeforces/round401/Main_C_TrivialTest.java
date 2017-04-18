package codeforces.round401;

import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class Main_C_TrivialTest {

	Random random = new Random();

	public void randomTest() {
		int n = random.nextInt(10) + 1;
		int m = random.nextInt(10) + 1;
		int[][] matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = random.nextInt();
			}
		}

		MatrixChecker trivial = new TrivialMatrixChecker(n, m, matrix);
		MatrixChecker tested = new TrivialMatrixChecker(n, m, matrix);

		printMatrix(n, m, matrix);
		System.out.println();
		for (int i = 0; i < 100_000; i++) {
			int l = random.nextInt(n);
			int r = random.nextInt(n - l) + l;
			System.out.println(l + " " + r);
			boolean trivialSorted = trivial.sorted(l, r);
			System.out.println(trivialSorted ? "Yes" : "No");
			boolean testedSorted = tested.sorted(l, r);
			if (testedSorted != trivialSorted) {
				System.out.println("Main_C_TrivialTest.randomTest rows: " + l + " " + r);
				System.out.println("trivial: " + trivialSorted + " tested: " + testedSorted + " i:" + i);
			}
			assertEquals(testedSorted, trivialSorted);
		}
	}

	private void printMatrix(int n, int m, int[][] matrix) {
		System.out.println(n + " " + m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void test() {
		int n = 4;
		int m = 1;
		int[][] matrix = new int[n][m];
		matrix[0] = new int[]{-1927296502};
		matrix[1] = new int[]{722463227};
		matrix[2] = new int[]{1592517900};
		matrix[3] = new int[]{-1186812662};
		MatrixChecker checker = new TrivialMatrixChecker(n, m, matrix);
		assertTrue(checker.sorted(1, 2));
		assertFalse(checker.sorted(1, 3));
		assertTrue(checker.sorted(1, 2));
	}


	public static class TrivialMatrixChecker implements MatrixChecker {

		private final int n;
		private final int m;
		private final int[][] matrix;

		public TrivialMatrixChecker(int n, int m, int[][] matrix) {
			this.n = n;
			this.m = m;
			this.matrix = matrix;
		}

		@Override
		public boolean sorted(int l, int r) {
			outer:
			for (int i = 0; i < m; i++) {
				for (int j = l + 1; j <= r; j++) {
					if (matrix[j][i] < matrix[j - 1][i]) {
						continue outer;
					}
				}
				return true;
			}
			return false;
		}
	}
}
