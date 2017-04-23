package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1120 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());

		int n2 = n * 2;
		int max = (int) Math.sqrt(n2);

		for (int p = max; p > 0; p--) {
			if (n2 % p == 0) {
				int a2 = (n2 / p - p + 1);
				if (a2 % 2 == 0) {
					int a = a2 / 2;
					System.out.println(a + " " + p);
					return;
				}
			}
		}
	}
}
