package codeforces.round400;

import java.io.*;

public class Main_A {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String[] victims = in.readLine().split(" ");
		int n = Integer.parseInt(in.readLine());
		out.print(victims[0]);
		out.print(' ');
		out.println(victims[1]);

		for (int i = 0; i < n; i++) {
			String[] day = in.readLine().split(" ");
			String killed = day[0];
			String nextVictim = day[1];

			if (victims[0].equals(killed)) {
				victims[0] = nextVictim;
			} else {
				victims[1] = nextVictim;
			}

			out.print(victims[0]);
			out.print(' ');
			out.println(victims[1]);
		}

		out.flush();
	}

}
