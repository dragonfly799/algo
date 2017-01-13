package codeforces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main_681C {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		List<String> result = new ArrayList<>();
		Heap heap = new Heap();

		for (int i = 0; i < n; i++) {
			String line = reader.readLine();
			if (line.equals("removeMin")) {
				if (heap.isEmpty()) {
					result.add("insert -1000000000");
				} else {
					heap.removeMin();
				}
			} else if (line.startsWith("insert")) {
				heap.insert(Integer.parseInt(line.substring(7)));
			} else { // getMin
				int expected = Integer.parseInt(line.substring(7));
				while (!heap.isEmpty() && heap.getMin() < expected) {
					heap.removeMin();
					result.add("removeMin");
				}
				if (heap.isEmpty() || heap.getMin() > expected) {
					result.add("insert " + expected);
					heap.insert(expected);
				}
			}
			result.add(line);
		}

		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		out.println(result.size());
		for (String line : result) {
			out.println(line);
		}
		out.flush();
	}

	private static class Heap {
		ArrayList<Integer> heap = new ArrayList<>();

		public Heap() {
			heap.add(0);
		}

		public boolean isEmpty() {
			return heap.size() < 2;
		}

		public int getMin() {
			return heap.get(1);
		}

		public void insert(int value) {
			heap.add(value);
			exchangeWithParent(heap.size() - 1);
		}

		public void removeMin() {
			if (heap.size() == 2) {
				heap.remove(1);
			} else {
				Integer last = heap.remove(heap.size() - 1);
				heap.set(1, last);
				exchangeWithChildren(1);
			}
		}

		private void exchangeWithParent(int position) {
			if (position == 1) return;

			int parentPosition = position / 2;
			if (heap.get(parentPosition) > heap.get(position)) {
				exchange(parentPosition, position);
				exchangeWithParent(parentPosition);
			}
		}

		private void exchangeWithChildren(int position) {
			int leftChildPosition = position * 2;
			int rightChildPosition = leftChildPosition + 1;

			if (leftChildPosition < heap.size()) {
				int minChildPosition = leftChildPosition;
				if (rightChildPosition < heap.size() && heap.get(rightChildPosition) < heap.get(leftChildPosition)) {
					minChildPosition = rightChildPosition;
				}
				if (heap.get(minChildPosition) < heap.get(position)) {
					exchange(minChildPosition, position);
					exchangeWithChildren(minChildPosition);
				}
			}
		}

		private void exchange(int position1, int position2) {
			Integer value = heap.get(position1);
			heap.set(position1, heap.get(position2));
			heap.set(position2, value);
		}
	}
}
