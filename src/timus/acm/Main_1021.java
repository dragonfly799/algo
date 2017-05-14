package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1021 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n1 = Integer.parseInt(in.readLine());
		int[] arr1 = new int[n1];
		for (int i = 0; i < n1; i++) {
			arr1[i] = Integer.parseInt(in.readLine());
		}

		int n2 = Integer.parseInt(in.readLine());
		int[] arr2 = new int[n2];
		for (int i = n2 - 1; i >= 0; i--) {
			arr2[i] = Integer.parseInt(in.readLine());
		}

		boolean res = (n1 < n2) ? check(n1, arr1, arr2) : check(n2, arr2, arr1);

		System.out.println(res ? "YES" : "NO");
	}

	private static boolean check(int n1, int[] arr1, int[] arr2) {
		for (int i = 0; i < n1; i++) {
			int target = 10000 - arr1[i];
			if (find(arr2, target)) {
				return true;
			}
		}
		return false;
	}

	private static boolean find(int[] arr, int target) {
		int l = 0;
		int r = arr.length - 1;

		while (l <= r) {
			int i = (l + r) / 2;
			if (arr[i] == target) {
				return true;
			}
			if (arr[i] < target) {
				l = i + 1;
			} else {
				r = i - 1;
			}
		}

		return false;
	}

}
