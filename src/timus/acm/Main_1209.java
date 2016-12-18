package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1209 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int n = readInt(reader);

		for (long i = 0; i < n; i++) {
			int k = readInt(reader);
			System.out.println(numberOnPosition(k - 1) + " ");
		}
	}

	private static byte numberOnPosition(long k) {
		long p = 8 * k + 1;
		long sqrt = (long) Math.sqrt(p);
		if (sqrt * sqrt == p) {
			return 1;
		}

		return 0;
	}

	private static int readInt(BufferedReader reader) throws IOException {
		return Integer.parseInt(reader.readLine());
	}

}
