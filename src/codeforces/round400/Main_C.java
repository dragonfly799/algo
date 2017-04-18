package codeforces.round400;

import java.io.*;

public class Main_C {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

		int n = readInt(in);
		int k = readInt(in);
		int[] sums = new int[n + 1];
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += readInt(in);
			sums[i] = sum;
		}

		int intervals = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (isPower(sums[i] - sums[j], k)) {
					intervals++;
				}
			}
		}

		System.out.println(intervals);
	}

	private static boolean isPower(int n, int k) {
		if (n == 0) {
			return false;
		}
		if (n == 1 || n == k) {
			return true;
		}
		if (k == 1) {
			return n == 1 || n == -1;
		}

		while(Math.abs(n) > 1) {
			if (n % k != 0) {
				return false;
			}
			n = n / k;
		}
		return n == 1 || n == k;
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}


}
