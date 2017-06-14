package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1260 {

	private static int[] nCache;
	private static int[] rCache;
	private static int[] sCache;
	private static int[] tCache;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		nCache = new int[n + 1];
		rCache = new int[n + 1];
		sCache = new int[n + 1];
		tCache = new int[n + 1];

		System.out.println(calc(n));
	}

	private static int calc(int n) {
		if (n < 3) {
			return 1;
		}

		int cached = nCache[n];
		if (cached > 0) {
			return cached;
		}

		int res = calc(n - 1) + r(n - 1) + s(n - 1);
		nCache[n] = res;
		return res;
	}

	private static int r(int n) {
		if (n == 1) { return 0; }
		if (n == 2) { return 1; }

		int cached = rCache[n];
		if (cached > 0) {
			return cached;
		}

		int res = t(n - 1);
		rCache[n] = res;
		return res;
	}

	private static int s(int n) {
		if (n < 3) {
			return 0;
		}

		int cached = sCache[n];
		if (cached > 0) {
			return cached;
		}

		int res = r(n - 1);
		sCache[n] = res;
		return res;
	}

	private static int t(int n) {
		if (n < 3) {
			return 1;
		}

		int cached = tCache[n];
		if (cached > 0) {
			return cached;
		}

		int res = s(n - 1) + r(n - 1) + s(n - 2);
		tCache[n] = res;
		return res;
	}

}
