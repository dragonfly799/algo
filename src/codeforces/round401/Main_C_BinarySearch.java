package codeforces.round401;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_C_BinarySearch {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int n = readInt(in);
		int m = readInt(in);
		int[][] matrix = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = readInt(in);
			}
		}

		ArrayList<Integer>[] breaks = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			breaks[i] = new ArrayList<>();
			for (int j = 1; j < n; j++) {
				if (matrix[j][i] < matrix[j - 1][i]) {
					breaks[i].add(j);
				}
			}
		}

		int k = readInt(in);
		for (int i = 0; i < k; i++) {
			int l = readInt(in) - 1;
			int r = readInt(in) - 1;

			boolean sorted = checkSorted(breaks, l, r);

			out.println((sorted ? "Yes" : "No"));
		}
		out.flush();
	}

	private static boolean checkSorted(ArrayList<Integer>[] breaks, int left, int right) {
		outer:
		for (int i = 0; i < breaks.length; i++) {
			int l = 0;
			int r = breaks[i].size() - 1;

			while (l <= r) {
				int m = (l + r) / 2;
				if (breaks[i].get(m) > left && breaks[i].get(m) <= right) {
					continue outer;
				}
				if (breaks[i].get(m) <= left) {
					l = m + 1;
				}
				if (breaks[i].get(m) > right) {
					r = m - 1;
				}
			}
			return true;
		}
		return false;
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

}
