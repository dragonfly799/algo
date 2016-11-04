package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_456A {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(tokenizer);

		boolean happyAlex = false;
		for (int i = 0; i < n; i++) {
			int a = readInt(tokenizer);
			int b = readInt(tokenizer);
			if (a != b) {
				happyAlex = true;
			}
		}
		System.out.println(happyAlex ? "Happy Alex" : "Poor Alex");
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

}
