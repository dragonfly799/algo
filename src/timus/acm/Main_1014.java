package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Произведение цифр
 * http://acm.timus.ru/problem.aspx?space=1&num=1014
 */
public class Main_1014 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		if (n == 0) {
			System.out.println(10);
		} else if (n < 10) {
			System.out.println(n);
		} else {
			long result = 0;
			long multiplier = 1;
			for (int i = 9; i > 1; i--) {
				while (n > 1 && n % i == 0) {
					result = i * multiplier + result;
					multiplier *= 10;
					n = n / i;
				}
			}
			if (n != 1) {
				System.out.println(-1);
			} else {
				System.out.println(result);
			}
		}
	}
}