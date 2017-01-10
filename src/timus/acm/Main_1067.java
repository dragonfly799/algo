package timus.acm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main_1067 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		TreeNode root = new TreeNode("root");
		for (int i = 0; i < n; i++) {
			String line = reader.readLine();

			addToTree(line, root);
		}

		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		printTree(root, out, 0);
		out.flush();
	}

	private static void printTree(TreeNode node, PrintWriter out, int level) {
		for (TreeNode child : node.children) {
			for (int i = 0; i < level; i++) {
				out.print(" ");
			}
			out.println(child.name);
			printTree(child, out, level + 1);
		}
	}

	private static void addToTree(String line, TreeNode node) {
		int indexSlash = line.indexOf('\\');
		String name = (indexSlash > 0) ? line.substring(0, indexSlash) : line;
		TreeNode child = findOrCreateChild(node, name);
		if (indexSlash > 0) {
			addToTree(line.substring(indexSlash + 1), child);
		}
	}

	private static TreeNode findOrCreateChild(TreeNode node, String name) {
		List<TreeNode> children = node.children;
		int l = 0;
		int r = children.size() - 1;

		while (l <= r) {
			int i = (l + r) / 2;

			if (children.get(i).name.equals(name)) {
				return children.get(i);
			}

			if (children.get(i).name.compareTo(name) < 0) {
				l = i + 1;
			} else {
				r = i - 1;
			}
		}

		TreeNode result = new TreeNode(name);
		children.add(l, result);
		return result;
	}

	private static class TreeNode {
		String name;
		List<TreeNode> children = new ArrayList<>();

		public TreeNode(String name) {
			this.name = name;
		}
	}

}
