package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1353 {

	private static int[][] cache = new int[82][10];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(in.readLine());

		if (s == 1) {
			System.out.println(10);
		} else {
			System.out.println(calc(s, 9));
		}
	}

	private static int calc(int s, int n) {
		if (s < 0 || s > 9 * n) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		if (cache[s][n] > 0) {
			return cache[s][n];
		}

		int res = 0;
		for (int i = 0; i < 10; i++) {
			res += calc(s - i, n - 1);
		}
		cache[s][n] = res;
		return res;
	}
}