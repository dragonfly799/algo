package codeforces.round400;

import java.io.*;

public class Main_B {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(in.readLine());
		int[] colors = new int[n];

		int delta = 2;
		int usedColors = 1;

		for (int i = 0; i < n; i++) {
			int price = i;
			int color = 1;
			if (colors[price] == 0) {
				colors[price] = color;
				price += delta;

				while (price < n) {
					if (colors[price] == 0 || colors[price] == color) {
						colors[price] = color + 1;
						usedColors = Math.max(usedColors, color + 1);
					}
					price += delta;
				}
			}
			delta++;
		}

		out.println(usedColors);
		for (int i = 0; i < n; i++) {
			out.print(colors[i]);
			out.print(' ');
		}
		out.flush();
	}

}
