package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collection;

public class Main_1203 {

	private static final int SIZE = 30_001;
	private static Node[] array = new Node[SIZE];
	private static Integer[] cache = new Integer[SIZE];

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(in);

		int maxEnd = 0;
		for (int i = 0; i < n; i++) {
			int start = readInt(in);
			int end = readInt(in);
			getNode(end).ends.add(start);
			if (end > maxEnd) {
				maxEnd = end;
			}
		}

		System.out.println(calc(maxEnd));
	}

	private static int calc(int n) {
		int i = 0;
		int res = 0;

		while (i <= n) {
			Node node = array[i];
			if (node != null) {
				for (Integer end : node.ends) {
					res = Math.max(res, cache[end - 1] + 1);
				}
			}
			cache[i] = res;
			i++;
		}
		return res;
	}

	private static Node getNode(int i) {
		Node node = array[i];
		if (node == null) {
			node = new Node();
			array[i] = node;
		}
		return node;
	}

	private static int readInt(StreamTokenizer in) throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	private static class Node {
		Collection<Integer> ends = new ArrayList<>();
	}
}
