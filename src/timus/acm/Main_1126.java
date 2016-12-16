package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1126 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int m = readInt(reader);
        List<Integer> data = new ArrayList<>();
        int n = readInt(reader);
        while (n != -1) {
            data.add(n);
            n = readInt(reader);
        }

        Node root = createNode(data, 0, data.size() - 1, null);

        for (int i = 0; i < data.size() - m; i++) {
            System.out.println(findMax(root, i, i + m - 1, 0, data.size() - 1));
        }
    }

    private static int findMax(Node node, int from, int to, int intervalStart, int intervalEnd) {
        int middle = intervalStart + intervalEnd / 2;
        if (from <= intervalStart && to >= intervalEnd) {
            return node.max;
        }
        if (from > middle) {
            return findMax(node.rightChild, from, to, middle + 1, intervalEnd);
        } else if (to <= middle) {
            return findMax(node.leftChild, from, to, intervalStart, middle);
        } else {
            return findMax(node.rightChild, from, to, middle + 1, intervalEnd) + findMax(node.leftChild, from, to, intervalStart, middle);
        }
    }

    private static Node createNode(List<Integer> data, int from, int to, Node parent) {
        Node node = new Node();
        node.parent = parent;
        if (from == to) {
            node.max = data.get(from);
        } else {

            int middle = (from + to) / 2;
            node.leftChild = createNode(data, from, middle, node);
            node.rightChild = createNode(data, middle + 1, to, node);
            node.max = Math.max(node.leftChild.max, node.rightChild.max);
        }

        return node;
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static class Node {
        int max;
        Node parent;
        Node leftChild;
        Node rightChild;
    }
}
