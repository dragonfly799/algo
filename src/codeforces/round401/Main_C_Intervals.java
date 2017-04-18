package codeforces.round401;

import java.io.*;
import java.util.BitSet;

public class Main_C_Intervals {

	private static int n;
	private static int m;
	private static int[][] matrix;

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		n = readInt(in);
		m = readInt(in);
		matrix = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = readInt(in);
			}
		}

		Node node = createNode(0, n - 1);

		int k = readInt(in);
		for (int i = 0; i < k; i++) {
			int l = readInt(in) - 1;
			int r = readInt(in) - 1;

			out.println(sorted(node, l, r) ? "Yes" : "No");
		}
		out.flush();
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static class Node {
		BitSet sorted;
		BitSet sortedWithPrev;

		Node leftChild;
		Node rightChild;
	}

	private static boolean sorted(Node node, int l, int r) {
		return !checkSorted(node, l, r, 0, n - 1).isEmpty();
	}

	private static Node createNode(int from, int to) {
		Node node = new Node();
		node.sorted = new BitSet(m);
		node.sorted.set(0, m);

		node.sortedWithPrev = new BitSet(m);
		if (from == 0) {
			node.sortedWithPrev.set(0, m);
		} else {
			for (int i = 0; i < m; i++) {
				if (matrix[from][i] >= matrix[from - 1][i]) {
					node.sortedWithPrev.set(i);
				}
			}
		}

		if (from < to) {
			int middle = (from + to) / 2;
			node.leftChild = createNode(from, middle);
			node.rightChild = createNode(middle + 1, to);

			for (int i = 0; i < m; i++) {
				node.sorted.and(node.leftChild.sorted);
				node.sorted.and(node.rightChild.sorted);
				node.sorted.and(node.rightChild.sortedWithPrev);
			}
		}
		return node;
	}

	private static BitSet checkSorted(Node node, int from, int to, int intervalStart, int intervalEnd) {
		if (node == null) {
			BitSet bitSet = new BitSet(m);
			bitSet.set(0, m);
			return bitSet;
		}

		int middle = (intervalStart + intervalEnd) / 2;
		if (from <= intervalStart && to >= intervalEnd) {
			return (BitSet) node.sorted.clone();
		}

		if (to <= middle) {
			return checkSorted(node.leftChild, from, to, intervalStart, middle);
		} else if (from > middle) {
			return checkSorted(node.rightChild, from, to, middle + 1, intervalEnd);
		} else {
			BitSet bitSet = checkSorted(node.leftChild, from, to, intervalStart, middle);
			bitSet.and(checkSorted(node.rightChild, from, to, middle + 1, intervalEnd));
			bitSet.and(node.rightChild.sortedWithPrev);
			return bitSet;
		}
	}

}
