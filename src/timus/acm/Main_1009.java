package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1009 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int k = Integer.parseInt(in.readLine());

		long res = calc(n - 1, k) * (k - 1);
		System.out.println(res);
	}

	private static long calc(int n, int k) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return k;
		}
		return (k - 1) * (calc(n - 1, k) + calc(n - 2, k));
	}

}
