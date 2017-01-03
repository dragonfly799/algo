package timus.acm;

import java.io.*;

public class Main_1028_Intervals {

	private static final int MAX_X = 32000;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(reader.readLine());
		int[] starsByLevel = new int[n];
		Node root = createTree();

		for (int i = 0; i < n; i++) {
			String line = reader.readLine();
			int x = Integer.parseInt(line.substring(0, line.indexOf(' ')));

			int sum = findSum(root, x, 0, MAX_X);
			starsByLevel[sum]++;
			update(root, x, 0, MAX_X);
		}

		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			out.println(starsByLevel[i]);
		}
		out.flush();
	}

	private static void update(Node node, int x, int intervalStart, int intervalEnd) {
		if (node == null || x > intervalEnd || x < intervalStart) {
			return;
		}
		int middle = (intervalStart + intervalEnd) / 2;
		node.sum++;
		update(node.leftChild, x, intervalStart, middle);
		update(node.rightChild, x, middle + 1, intervalEnd);
	}

	private static int findSum(Node node, int to, int intervalStart, int intervalEnd) {
		if (node == null) {
			return 0;
		}
		if (to >= intervalEnd) {
			return node.sum;
		}
		int middle = (intervalStart + intervalEnd) / 2;
		if (to <= middle) {
			return findSum(node.leftChild, to, intervalStart, middle);
		} else {
			return findSum(node.leftChild, to, intervalStart, middle)
					+ findSum(node.rightChild, to, middle + 1, intervalEnd);
		}
	}

	private static Node createTree() {
		return createNode(0, MAX_X);
	}

	private static Node createNode(int from, int to) {
		Node node = new Node();
		if (from != to) {
			int middle = (from + to) / 2;
			node.leftChild = createNode(from, middle);
			node.rightChild = createNode(middle + 1, to);
		}
		return node;
	}

	private static class Node {
		int sum;
		Node leftChild;
		Node rightChild;
	}

}
