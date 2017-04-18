package codeforces.round401;

import java.io.*;

public class Main_C_BreakSums {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int n = readInt(in);
		int m = readInt(in);
		int[][] breaks = fillBreaks(in, n, m);

		int k = readInt(in);
		for (int i = 0; i < k; i++) {
			int l = readInt(in) - 1;
			int r = readInt(in) - 1;

			boolean sorted = checkSorted(breaks, m, l, r);

			out.println((sorted ? "Yes" : "No"));
		}
		out.flush();
	}

	private static int[][] fillBreaks(StreamTokenizer in, int n, int m) throws IOException {
		int[][] breaks = new int[n][m];

		int[] row = new int[m];
		for (int i = 0; i < m; i++) {
			row[i] = readInt(in);
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int num = readInt(in);
				if (num < row[j]) {
					breaks[i][j] = breaks[i - 1][j] + 1;
				} else {
					breaks[i][j] = breaks[i - 1][j];
				}
				row[j] = num;
			}
		}
		return breaks;
	}

	private static boolean checkSorted(int[][] matrixBreaks, int columns, int l, int r) {
		for (int i = 0; i < columns; i++) {
			if (matrixBreaks[r][i] == matrixBreaks[l][i]) {
				return true;
			}
		}
		return false;
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

}
