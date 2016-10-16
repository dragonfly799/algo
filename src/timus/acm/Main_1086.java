package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Криптография
 * http://acm.timus.ru/problem.aspx?space=1&num=1086
 */
public class Main_1086 {
	private static int[] primes = new int[15000];
	private static int lastCalculated = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(reader.readLine());
		primes[0] = 2;
		primes[1] = 3;

		for (int i = 0; i < k; i++) {
			int n = Integer.parseInt(reader.readLine());

			if (n - 1 <= lastCalculated) {
				System.out.println(primes[n - 1]);
			} else {
				System.out.println(calculatePrimes(n - 1));
			}
		}
	}

	private static int calculatePrimes(int index) {
		int newPrime = primes[lastCalculated];
		while (lastCalculated < index) {
			newPrime += 2;
			boolean isPrime = true;
			for (int i = 0; i < lastCalculated; i++) {
				if (primes[i] * primes[i] > newPrime) {
					break;
				}
				if (newPrime % primes[i] == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				lastCalculated++;
				primes[lastCalculated] = newPrime;
			}
		}
		return primes[index];
	}
}
