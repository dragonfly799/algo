package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Cable Master
 * http://acm.timus.ru/problem.aspx?space=1&num=1184
 */
public class Main_1184 {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(tokenizer);
		int k = readInt(tokenizer);

		int[] cables = readArray(n, tokenizer);

		System.out.printf("%.2f", ((double) searchMaxLength(cables, k)) / 100);
	}

	private static int searchMaxLength(int[] cables, int count) {
		int left = 1;
		int right = 10000000;
		while (left <= right) {
			int l = (left + right) / 2;
			if (canMakeCables(cables, count, l)) {
				if (!canMakeCables(cables, count, l + 1)) {
					return l;
				} else {
					left = l + 1;
				}
			} else {
				right = l - 1;
			}
		}
		return 0;
	}

	private static boolean canMakeCables(int[] cables, int count, int length) {
		int n = 0;
		for (int i = 0; i < cables.length; i++) {
			n += cables[i] / length;
			if (n >= count) {
				return true;
			}
		}
		return false;
	}

	private static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static double readDouble(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return tokenizer.nval;
	}

	private static int[] readArray(int size, StreamTokenizer tokenizer) throws IOException {
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = (int) (readDouble(tokenizer) * 100);
		}
		return result;
	}
}
