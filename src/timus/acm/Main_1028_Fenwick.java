package timus.acm;

import java.io.*;

public class Main_1028_Fenwick {

	private static final int MAX_X = 32000;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] starsByLevel = new int[n];

		int size = MAX_X + 1;
		int[] fTree = new int[size];

		for (int i = 0; i < n; i++) {
			String line = reader.readLine();
			int x = Integer.parseInt(line.substring(0, line.indexOf(' ')));

			int pos = sum(fTree, x);
			starsByLevel[pos]++;
			update(fTree, size, x);
		}

		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			out.println(starsByLevel[i]);
		}
		out.flush();
	}

	private static int sum(int[] fTree, int x) {
		int result = 0;
		int i = x;
		while (i >= 0) {
			result += fTree[i];
			i = (i & (i + 1)) - 1;
		}
		return result;
	}

	private static void update(int[] fTree, int size, int x) {
		int i = x;
		while (i < size) {
			fTree[i]++;
			i = i | (i + 1);
		}
	}

}
