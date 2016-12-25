package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1028_Intervals {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] starsByLevel = new int[n];

		for (int i = 0; i < n; i++) {
			String line = reader.readLine();
			int x = Integer.parseInt(line.substring(0, line.indexOf(' ')));
			int y = Integer.parseInt(line.substring(line.indexOf(' ') + 1));


		}
	}
}
