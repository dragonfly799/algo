package timus.acm;

import java.io.*;

public class Main_1079 {

	private static final int SIZE = 100000;
	private static int[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		array = fillArray();
		Node node = createNode(0, SIZE - 1);

		int n = Integer.parseInt(in.readLine());
		while (n != 0) {
			out.println(findMax(node, n, 0, SIZE - 1));
			n = Integer.parseInt(in.readLine());
		}

		out.flush();
	}

	private static int[] fillArray() {
		int[] array = new int[SIZE];
		array[1] = 1;
		for (int i = 2; i < SIZE; i++) {
			if (i % 2 == 0) {
				array[i] = array[i / 2];
			} else {
				int p = i / 2;
				array[i] = array[p] + array[p + 1];
			}
		}
		return array;
	}

	private static Node createNode(int from, int to) {
		Node node = new Node();
		if (from == to) {
			node.max = array[from];
		} else {
			int middle = (from + to) / 2;
			node.leftChild = createNode(from, middle);
			node.rightChild = createNode(middle + 1, to);
			node.max = Math.max(node.leftChild.max, node.rightChild.max);
		}
		return node;
	}

	private static int findMax(Node node, int to, int intervalStart, int intervalEnd) {
		if (node == null) {
			return 0;
		}
		if (to >= intervalEnd) {
			return node.max;
		}
		int middle = (intervalStart + intervalEnd) / 2;
		if (to <= middle) {
			return findMax(node.leftChild, to, intervalStart, middle);
		} else {
			return Math.max(
					findMax(node.leftChild, to, intervalStart, middle),
					findMax(node.rightChild, to, middle + 1, intervalEnd)
			);
		}
	}

	private static class Node {
		int max;
		Node leftChild;
		Node rightChild;
	}
}
