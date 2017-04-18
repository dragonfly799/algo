package codeforces.round401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] sherlocksCard = new int[n];
		int[] moriartisCardCyphers1 = new int[10];

		String sher = in.readLine();
		for (int i = 0; i < n; i++) {
			sherlocksCard[i] = sher.charAt(i) - '0';
		}
		Arrays.sort(sherlocksCard);

		String mor = in.readLine();
		for (int i = 0; i < n; i++) {
			moriartisCardCyphers1[mor.charAt(i) - '0']++;
		}
		int[] moriartisCardCyphers2 = Arrays.copyOf(moriartisCardCyphers1, 10);

		int minClaps = 0;
		outer:
		for (int i = n - 1; i >= 0; i--) {
			int num = sherlocksCard[i];

			if (moriartisCardCyphers1[num] > 0) {
				moriartisCardCyphers1[num]--;
			} else {
				for (int j = num; j < 10; j++) {
					if (moriartisCardCyphers1[j] > 0) {
						moriartisCardCyphers1[j]--;
						continue outer;
					}
				}
				for (int j = 0; j < num; j++) {
					if (moriartisCardCyphers1[j] > 0) {
						moriartisCardCyphers1[j]--;
						minClaps++;
						continue outer;
					}
				}
			}
		}


		System.out.println(minClaps);

		int maxClaps = 0;
		outer:
		for (int i = n - 1; i >= 0; i--) {
			int num = sherlocksCard[i];
			for (int j = num + 1; j < 10; j++) {
				if (moriartisCardCyphers2[j] > 0) {
					moriartisCardCyphers2[j]--;
					maxClaps++;
					continue outer;
				}
			}

			for (int j = 0; j <= num; j++) {
				if (moriartisCardCyphers2[j] > 0) {
					moriartisCardCyphers2[j]--;
					continue outer;
				}
			}
		}

		System.out.println(maxClaps);
	}

}
