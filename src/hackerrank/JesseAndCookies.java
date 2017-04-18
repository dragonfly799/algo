package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

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
		int[] heap;
		int size;

		public Heap(int n) {
			heap = new int[n + 1];
			size = 1;
		}

		public int size() {
			return size - 1;
		}

		public int top() {
			return heap[1];
		}

		public void add(int value) {
			heap[size] = value;
			size++;
			exchangeWithParent(size - 1);
		}

		public int removeTop() {
			int result = heap[1];
			size--;
			if (size == 1) {
				return result;
			}

			heap[1] = heap[size];
			exchangeWithChildren(1);
			return result;
		}

		private void exchangeWithChildren(int position) {
			int leftChild = position * 2;
			int rightChild = leftChild + 1;
			if (leftChild < size) {
				int minChild = leftChild;
				if (rightChild < size && heap[rightChild] < heap[leftChild]) {
					minChild = rightChild;
				}

				if (heap[position] > heap[minChild]) {
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
			if (heap[parent] > heap[position]) {
				exchange(parent, position);
				exchangeWithParent(parent);
			}
		}

		private void exchange(int position1, int position2) {
			int value1 = heap[position1];
			heap[position1] = heap[position2];
			heap[position2] = value1;
		}

	}

}
