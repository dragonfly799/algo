package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1197 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = readInt(reader);

		for (int i = 0; i < n; i++) {
			String s = reader.readLine();
			int col = s.charAt(0) - 'a';
			int row = s.charAt(1) - '1';

			int res = 0;
			int toVerticalBorder = Math.min(Math.min(col, 7 - col), 2);
			int toHorizontalBorder = Math.min(Math.min(row, 7 - row), 2);

			switch (toHorizontalBorder + toVerticalBorder) {
				case 0: res = 2; break;
				case 1: res = 3; break;
				case 2: res = 4; break;
				case 3: res = 6; break;
				case 4: res = 8; break;
			}

			System.out.println(res);
		}
	}

	private static int readInt(BufferedReader reader) throws IOException {
		return Integer.parseInt(reader.readLine());
	}
}
