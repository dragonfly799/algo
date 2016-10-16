package timus.acm;

import utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1082_Program {
	private static int N = 3;
	private static long c;
	private static int A[];

	public static void main(String[] args) throws IOException {
		c = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		A = StreamUtils.readArray(N, new StreamTokenizer(reader));

		Q(0, N - 1);
		if (c == (N * N + 3 * N - 4) / 2) {
			System.out.println("Василиса Прекрасная");
		} else {
			System.out.println("Кощей Бессмертный");
		}
	}

	static int P(int l, int r) {
		int x = A[l],
				i = l - 1,
				j = r + 1,
				t;
		while (true) {
			do {
				--j;
				++c;
			}
			while (A[j] > x);
			do {
				++i;
				++c;
			}
			while (A[i] < x);
			if (i < j) {
				t = A[i];
				A[i] = A[j];
				A[j] = t;
			} else { return j; }
		}
	}

	static void Q(int l, int r) {
		int n;
		if (l < r) {
			n = P(l, r);
			Q(l, n);
			Q((n + 1), r);
		}
	}

}
