package codeforces;

import java.io.*;

public class Main_339D {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int n = readInt(in);
		int m = readInt(in);

		int size = 1 << n;
		int[] array = readIntArray(in, size);

		TreeNode root = calculate(array, n, 0, size - 1);

		for (int i = 0; i < m; i++) {
			int index = readInt(in) - 1;
			int newValue = readInt(in);

			array[index] = newValue;
			update(root, array, n, index, 0, size - 1);
			out.println(root.value);
		}

		out.flush();
	}

	private static void update(TreeNode node, int[] array, int level, int index, int intervalStart, int intervalEnd) {
		if (intervalStart == intervalEnd) {
			node.value = array[index];
			return;
		}

		int middle = (intervalStart + intervalEnd) / 2;
		if (index > middle) {
			update(node.rightChild, array, level - 1, index, middle + 1, intervalEnd);
		} else {
			update(node.leftChild, array, level - 1, index, intervalStart, middle);
		}
		calculateNodeValue(node, level);
	}

	private static TreeNode calculate(int[] array, int level, int intervalStart, int intervalEnd) {
		TreeNode node = new TreeNode();

		if (intervalStart == intervalEnd) {
			node.value = array[intervalStart];
			return node;
		}

		int middle = (intervalStart + intervalEnd) / 2;
		node.leftChild = calculate(array, level - 1, intervalStart, middle);
		node.rightChild = calculate(array, level - 1, middle + 1, intervalEnd);

		calculateNodeValue(node, level);

		return node;
	}

	private static void calculateNodeValue(TreeNode node, int level) {
		node.value = (level % 2 == 0)
				? node.leftChild.value ^ node.rightChild.value
				: node.leftChild.value | node.rightChild.value;
	}

	private static int[] readIntArray(StreamTokenizer in, int size) throws IOException {
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = readInt(in);
		}
		return result;
	}

	private static int readInt(StreamTokenizer in) throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	private static class TreeNode {
		int value;
		TreeNode leftChild;
		TreeNode rightChild;
	}
}
