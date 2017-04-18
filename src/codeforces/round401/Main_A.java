package codeforces.round401;

import java.io.*;

public class Main_A {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		n = n % 6;

		int x = Integer.parseInt(in.readLine());
		int[] arr = new int[3];
		arr[x] = 1;

		boolean odd = n % 2 == 0;
		for (int i = 0; i < n; i++) {
			if (odd) {
				int v = arr[1];
				arr[1] = arr[2];
				arr[2] = v;
			} else {
				int v = arr[1];
				arr[1] = arr[0];
				arr[0] = v;
			}
			odd = !odd;
		}

		int num = arr[0] == 1 ? 0 : (arr[1] == 1) ? 1 : 2;
		System.out.println(num);
	}

}
