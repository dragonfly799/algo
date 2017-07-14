package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1167 {

	private static Integer[][] unhappyCache;
	private static Integer[][] minCache;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(in);
		int k = readInt(in);

		boolean[] horses = new boolean[n];
		for (int i = 0; i < n; i++) {
			horses[i] = readInt(in) == 1;
		}

		minCache = new Integer[n + 1][k + 1];
		unhappyCache = new Integer[n][n + 1];

		System.out.println(calcMin(horses, n, k));
	}

	private static int calcMin(boolean[] horses, int n, int k) {
		Integer cached = minCache[n][k];
		if (cached != null) {
			return cached;
		}

		int min;
		if (k == 1) {
			min = calcUnhappy(horses, horses.length - n, horses.length);
		} else {
			min = Integer.MAX_VALUE;
			for (int i = 1; i <= n - k + 1; i++) {
				int curr = calcUnhappy(horses, horses.length - n, horses.length - n + i) + calcMin(horses, n - i, k - 1);
				if (curr < min) {
					min = curr;
				}
			}
		}
		minCache[n][k] = min;
		return min;
	}

	private static int calcUnhappy(boolean[] horses, int startIndex, int endIndex) {
		if (endIndex - startIndex == 1) {
			return 0;
		}
		Integer cached = unhappyCache[startIndex][endIndex];
		if (cached != null) {
			return cached;
		}
		int black = 0;
		int white = 0;
		for (int i = startIndex; i < endIndex; i++) {
			if (horses[i]) {
				black++;
			} else {
				white++;
			}
		}
		int res = black * white;
		unhappyCache[startIndex][endIndex] = res;
		return res;
	}

	private static int readInt(StreamTokenizer in) throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}
