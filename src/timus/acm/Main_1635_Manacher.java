package timus.acm;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main_1635_Manacher {

	static int[] nextIndexCache;
	private static int[] lengthCache;
	private static int[] palCache;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();

		int count = find(line);

		System.out.println(count);
		printCached(line);
	}

	public static int find(String line) {
		nextIndexCache = new int[line.length()];
		lengthCache = new int[line.length()];
		palCache = new int[line.length() * 2 + 1];

		findAllPalindromes(line);

		return find(line, 0);
	}

	private static void findAllPalindromes(String line) {
		String boundedLine = addBounds(line);
		findOddPalindromes(boundedLine);
	}

	private static String addBounds(String line) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < line.length(); i++) {
			sb.append('|').append(line.charAt(i));
		}
		sb.append('|');
		return sb.toString();
	}

	private static void findOddPalindromes(String line) {
		int l = 0;
		int r = -1;
		for (int center = 0; center < line.length(); center++) {
			int mirror = l + (r - center);
			int width = 1;
			if (center <= r) {
				width = palCache[mirror];
				if (mirror - width + 1 <= l) {
					width = mirror - l + 1;
				}
			}

			if (center > r || mirror - width + 1 <= l) {
				int start = center - width;
				int end = center + width;

				while (start >= 0 && end < line.length()) {
					if (line.charAt(start) != line.charAt(end)) {
						break;
					} else {
						width++;
						if (end > r) {
							l = start;
							r = end;
						}
						start--;
						end++;
					}
				}
			}
			palCache[center] = width;
		}
	}

	static int find(String line, int start) {
		if (start == line.length()) {
			return 0;
		}
		if (lengthCache[start] != 0) {
			return lengthCache[start];
		}

		int minCount = Integer.MAX_VALUE;
		int nextStart = -1;
		for (int i = line.length() - 1; i >= start; i--) {
			if (isPalindrome(start, i)) {

				int count = find(line, i + 1) + 1;
				if (count < minCount) {
					minCount = count;
					nextStart = i + 1;
				}
				if (count == 1) {
					break;
				}
			}
		}
		nextIndexCache[start] = nextStart;
		lengthCache[start] = minCount;
		return minCount;
	}

	private static boolean isPalindrome(int start, int end) {
		int center = start + end + 1;
		int width = end - start + 1;

		return palCache[center] > width;
	}

	private static void printCached(String line) {
		PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		int start = 0;
		while (start >= 0 && start < line.length()) {
			int next = nextIndexCache[start];
			for (int i = start; i < next; i++) {
				out.print(line.charAt(i));
			}
			out.print(' ');
			start = next;
		}
		out.flush();
	}

}