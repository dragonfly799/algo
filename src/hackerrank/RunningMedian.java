package hackerrank;

import java.io.*;
import java.util.ArrayList;

public class RunningMedian {
	private static HeapMax firstHeap = new HeapMax();
	private static HeapMin secondHeap = new HeapMin();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(in.readLine());

		for (int i = 0; i < n; i++) {
			int value = Integer.parseInt(in.readLine());

			updateHeaps(value);

			double median = i % 2 == 0 ? firstHeap.top() : (double) (firstHeap.top() + secondHeap.top()) / 2;
			out.printf("%.1f\n", median);
		}

		out.flush();
	}

	private static void updateHeaps(int value) {
		if (firstHeap.size() > secondHeap.size()) {
			if (value > firstHeap.top()) {
				secondHeap.add(value);
			} else {
				secondHeap.add(firstHeap.removeTop());
				firstHeap.add(value);
			}
		}

		else {
			if (secondHeap.isEmpty() || value <= secondHeap.top()) {
				firstHeap.add(value);
			} else {
				firstHeap.add(secondHeap.removeTop());
				secondHeap.add(value);
			}
		}
	}


	private static class HeapMax extends Heap {
		@Override
		protected boolean needExchange(int pos1, int pos2) {
			return heap.get(pos1) < heap.get(pos2);
		}
	}

	private static class HeapMin extends Heap {
		@Override
		protected boolean needExchange(int pos1, int pos2) {
			return heap.get(pos1) > heap.get(pos2);
		}
	}

	private abstract static class Heap {
		protected ArrayList<Integer> heap = new ArrayList<>();

		public Heap() {
			heap.add(0);
		}

		public boolean isEmpty() {
			return heap.size() == 1;
		}

		public int size() {
			return heap.size() - 1;
		}

		public int top() {
			return heap.get(1);
		}

		public void add(int value) {
			heap.add(value);
			exchangeWithParent(heap.size() - 1);
		}

		public int removeTop() {
			if (heap.size() == 2) {
				return heap.remove(1);
			}
			Integer last = heap.remove(heap.size() - 1);
			Integer result = heap.set(1, last);
			exchangeWithChildren(1);
			return result;
		}

		private void exchangeWithChildren(int pos) {
			int leftChild = pos * 2;
			int rightChild = leftChild + 1;
			if (leftChild < heap.size()) {
				int minChild = leftChild;
				if (rightChild < heap.size() && needExchange(leftChild, rightChild)) {
					minChild = rightChild;
				}

				if (needExchange(pos, minChild)) {
					exchange(pos, minChild);
					exchangeWithChildren(minChild);
				}
			}
		}

		protected abstract boolean needExchange(int pos1, int pos2);

		private void exchangeWithParent(int pos) {
			if (pos == 1) return;

			int parent = pos / 2;
			if (needExchange(parent, pos)) {
				exchange(parent, pos);
				exchangeWithParent(parent);
			}
		}

		private void exchange(int pos1, int pos2) {
			Integer value1 = heap.get(pos1);
			heap.set(pos1, heap.get(pos2));
			heap.set(pos2, value1);
		}

	}
}
