package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Танцы синуса
 * http://acm.timus.ru/problem.aspx?space=1&num=1149
 */
public class Main_1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		printS(n);
	}

	private static void printS(int n) {
		for (int i = 1; i < n; i++) {
			System.out.print("(");
		}
		printA(1);
		System.out.print("+" + n + "");
		for (int i = 2; i <= n; i++) {
			System.out.print(")");
			printA(i);
			System.out.print("+" + (n - i + 1));
		}
	}

	private static void printA(int n) {
		boolean minus = true;
		System.out.print("sin(" + 1);
		for (int i = 2; i <= n; i++) {
			System.out.print((minus ? "-" : "+") + "sin(" + i);
			minus = !minus;
		}
		for (int i = 0; i < n; i++) {
			System.out.print(")");
		}
	}

}
