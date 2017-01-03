package timus.acm;

import java.io.*;
import java.util.ArrayList;

public class Main_1028 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] starsByLevel = new int[n];
		ArrayList<Integer> arrayOfX = new ArrayList<>(n);

		for (int i = 0; i < n; i++) {
			String line = reader.readLine();
			int x = Integer.parseInt(line.substring(0, line.indexOf(' ')));

			int pos = insert(arrayOfX, x);
			starsByLevel[pos]++;
		}

		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			out.println(starsByLevel[i]);
		}
		out.flush();
	}

	private static int insert(ArrayList<Integer> list, int value) {
		int l = 0;
		int r = list.size() - 1;

		while (l <= r) {
			int i = (l + r) / 2;
			if (list.get(i) <= value && (i == list.size() - 1 || list.get(i + 1) > value)) {
				list.add(i + 1, value);
				return i + 1;
			}

			if (list.get(i) <= value) {
				l = i + 1;
			} else {
				r = i - 1;
			}
		}

		list.add(0, value);
		return 0;
	}
}
