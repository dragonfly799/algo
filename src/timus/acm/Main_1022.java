package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
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

		int level = 1;
		int i = 0;
		while (level <= nodeCount) {
			Node node = nodes[i];
			if (node.parents.isEmpty() && node.level == 0) {
				node.level = level;
				level++;
				for (Node child : node.children) {
					child.parents.remove(node);
				}
			}
			i++;
			if (i == nodeCount) {
				i = 0;
			}
		}

		Arrays.sort(nodes);

		for (i = 0; i < nodeCount; i++) {
			out.print(nodes[i].num);
			out.print(' ');
		}
		out.flush();
	}

	private static int readInt(StreamTokenizer in) throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	private static class Node implements Comparable<Node> {
		int level;
		int num;
		Collection<Node> parents = new ArrayList<>();
		Collection<Node> children = new ArrayList<>();

		private Node(int num) {
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			return this.level - o.level;
		}

	}

}
