package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_479C {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(tokenizer);

		Pair[] pairs = new Pair[n];

		for (int i = 0; i < n; i++) {
			pairs[i] = new Pair(readInt(tokenizer), readInt(tokenizer));
		}

		Arrays.sort(pairs);

		for (int i = 1; i < n; i++) {
			if (pairs[i].b < pairs[i - 1].b) {
				pairs[i].b = pairs[i].a;
			}
		}
		System.out.println(pairs[n - 1].b);
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static class Pair implements Comparable<Pair> {
		public int a;
		public int b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair o) {
			int res = a - o.a;
			if (res == 0) {
				res = b - o.b;
			}
			return res;
		}
	}

}
