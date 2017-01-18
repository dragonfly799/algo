package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class JesseAndCookies {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

		int n = readInt(in);
		int k = readInt(in);

		Heap heap = new Heap(n);

		for (int i = 0; i < n; i++) {
			int cookie = readInt(in);
			heap.add(cookie);
		}

		int operations = 0;
		while (heap.size() > 1 && heap.top() < k) {
			int cookie1 = heap.removeTop();
			int cookie2 = heap.removeTop();

			int newCookie = cookie1 + 2 * cookie2;
			heap.add(newCookie);
			operations++;
		}

		if (heap.top() < k) {
			operations = -1;
		}

		System.out.println(operations);
	}

	private static int readInt(StreamTokenizer in) throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	private static class Heap {

		ArrayList<Integer> heap;

		public Heap(int n) {
			heap = new ArrayList<>(n + 1);
			heap.add(0);
		}

		public int size() {
			return heap.size() - 1;
		}

		public int top() {
			return heap.get(1);
		}

		public void add(int value) {
			heap.add(value);
			int position = heap.size() - 1;
			exchangeWithParent(position);
		}

		public int removeTop() {
			if (size() == 1) {
				return heap.remove(1);
			}

			Integer lastElement = heap.remove(heap.size() - 1);
			Integer result = heap.set(1, lastElement);
			exchangeWithChildren(1);
			return result;
		}

		private void exchangeWithChildren(int position) {
			int leftChild = position * 2;
			int rightChild = leftChild + 1;
			if (leftChild < heap.size()) {
				int minChild = leftChild;
				if (rightChild < heap.size() && heap.get(rightChild) < heap.get(leftChild)) {
					minChild = rightChild;
				}

				if (heap.get(position) > heap.get(minChild)) {
					exchange(position, minChild);
					exchangeWithChildren(minChild);
				}
			}
		}

		private void exchangeWithParent(int position) {
			if (position == 1) {
				return;
			}
			int parent = position / 2;
			if (heap.get(parent) > heap.get(position)) {
				exchange(parent, position);
				exchangeWithParent(parent);
			}
		}

		private void exchange(int position1, int position2) {
			Integer value1 = heap.get(position1);
			heap.set(position1, heap.get(position2));
			heap.set(position2, value1);
		}

	}

}
