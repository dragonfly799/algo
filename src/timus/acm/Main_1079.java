package timus.acm;

import java.io.*;

public class Main_1079 {

	private static final int SIZE = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int[] array = fillArray(SIZE);
		updateToMax(array);

		int n = Integer.parseInt(in.readLine());
		while (n != 0) {
			out.println(array[n]);
			n = Integer.parseInt(in.readLine());
		}

		out.flush();
	}

	private static int[] fillArray(int size) {
		int[] array = new int[size];
		array[1] = 1;
		for (int i = 2; i < size; i++) {
			if (i % 2 == 0) {
				array[i] = array[i / 2];
			} else {
				int p = i / 2;
				array[i] = array[p] + array[p + 1];
			}
		}
		return array;
	}

	private static void updateToMax(int[] array) {
		for (int i = 2; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				array[i] = array[i - 1];
			}
		}
	}

}
