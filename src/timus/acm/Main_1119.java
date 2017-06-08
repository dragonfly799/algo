package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main_1119 {

    private static int maxDiagonals;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = readInt(in);
        int m = readInt(in);
        int k = readInt(in);

        Node[] diagonals = new Node[k];

        for (int i = 0; i < k; i++) {
            Node node = new Node(readInt(in), readInt(in));
            diagonals[i] = node;
            for (int j = 0; j < i; j++) {
                link(diagonals[j], node);
            }
        }

        for (Node node : diagonals) {
            if (node.parents == 0) {
                calc(node, 1);
            }
        }

        long path = Math.round((m + n + maxDiagonals * (Math.sqrt(2) - 2)) * 100);
        System.out.println(path);
    }

    private static void calc(Node node, int path) {
        if (path > node.path) {
            node.path = path;
            maxDiagonals = Math.max(maxDiagonals, path);
            for (Node child : node.children) {
                calc(child, path + 1);
            }
        }
    }

    private static void link(Node node1, Node node2) {
        if (node1.x < node2.x && node1.y < node2.y) {
            node1.children.add(node2);
            node2.parents++;
        } else if (node2.x < node1.x && node2.y < node1.y) {
            node2.children.add(node1);
            node1.parents++;
        }
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static class Node {
        int x;
        int y;
        int parents;
        int path;
        List<Node> children = new ArrayList<>();

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
