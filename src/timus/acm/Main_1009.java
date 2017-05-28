package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1009 {

	private static long[] cache;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int k = Integer.parseInt(in.readLine());

		cache = new long[n];

		long res = calcRec(n - 1, k) * (k - 1);
		System.out.println(res);
	}

	private static long calcIter(int n, int k) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return k;
		}

		long p1 = 1;
		long p2 = k;
		long res = p2;

		for (int i = 2; i <= n; i++) {
			res = (k - 1) * (p2 + p1);
			p1 = p2;
			p2 = res;
		}

		return res;
	}

	private static long calcRec(int n, int k) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return k;
		}
		if (cache[n] > 0) {
			return cache[n];
		}

		long res = (k - 1) * (calcRec(n - 1, k) + calcRec(n - 2, k));
		cache[n] = res;
		return res;
	}

}
