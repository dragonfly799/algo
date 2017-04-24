package timus.acm;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main_1022 {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int nodeCount = readInt(in);
		Node[] nodes = new Node[nodeCount];
		for (int i = 0; i < nodeCount; i++) {
			nodes[i] = new Node(i + 1);
		}

		for (int i = 0; i < nodeCount; i++) {
			int n;
			while ((n = readInt(in)) != 0) {
				nodes[i].children.add(nodes[n - 1]);
				nodes[n - 1].parents.add(nodes[i]);
			}
		}

		int i = 0;
		int printed = 0;
		while (printed < nodeCount) {
			Node node = nodes[i];
			if (node != null && node.parents.isEmpty()) {
				out.print(nodes[i].num);
				out.print(' ');

				for (Node child : node.children) {
					child.parents.remove(node);
				}
				nodes[i] = null;
				printed++;
			}
			i++;
			if (i == nodeCount) {
				i = 0;
			}
		}

		out.flush();
	}

	private static int readInt(StreamTokenizer in) throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	private static class Node {
		int num;
		Collection<Node> parents = new ArrayList<>();
		Collection<Node> children = new ArrayList<>();

		private Node(int num) {
			this.num = num;
		}

	}

}
