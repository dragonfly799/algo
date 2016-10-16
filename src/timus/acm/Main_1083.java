package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1083 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = reader.readLine();
		int spaceIndex = line.indexOf(' ');
		int n = Integer.parseInt(line.substring(0, spaceIndex));
		int k = line.length() - spaceIndex - 1;

		int result = 1;
		while (n > 1) {
			result *= n;
			n -= k ;
		}

		System.out.println(result);
	}

}
