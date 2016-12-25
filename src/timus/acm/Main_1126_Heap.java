package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1126_Heap {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int m = readInt(reader);
		Heap heap = new Heap();

		int n = readInt(reader);
		int length = 0;
		while (n != -1) {
			heap.add(length, n);
			length++;

			if (length - m > 0) {
				heap.remove(length - m - 1);
			}
			if (length - m >= 0) {
				System.out.println(heap.top());
			}
			n = readInt(reader);
		}
	}

	private static int readInt(BufferedReader reader) throws IOException {
		return Integer.parseInt(reader.readLine());
	}


	private static class Heap {
		private ArrayList<Integer> heap = new ArrayList<>();
		private ArrayList<Integer> indexToHeapPosition = new ArrayList<>();
		private ArrayList<Integer> heapPositionToIndex = new ArrayList<>();

		public Heap() {
			heap.add(0);
			heapPositionToIndex.add(0);
		}

		public void add(int index, int value) {
			heap.add(value);
			int position = heap.size() - 1;
			indexToHeapPosition.add(index, position);
			heapPositionToIndex.add(position, index);
			exchangeWithParentIfNeed(position);
		}

		public void remove(int index) {
			Integer position = indexToHeapPosition.get(index);
			int lastPosition = heap.size() - 1;

			Integer lastValue = heap.remove(lastPosition);
			if (position != lastPosition) {
				heap.set(position, lastValue);

				Integer lastIndex = heapPositionToIndex.get(lastPosition);
				indexToHeapPosition.set(lastIndex, position);
				heapPositionToIndex.set(position, lastIndex);

				exchangeWithParentIfNeed(position);
				exchangeWithChildrenIfNeed(position);
			}
		}

		private void exchangeWithChildrenIfNeed(int position) {
			int leftChildPosition = position * 2;
			int rightChildPosition = leftChildPosition + 1;
			if (leftChildPosition < heap.size()) {
				int maxChildPosition = leftChildPosition;
				if (rightChildPosition < heap.size()) {
					if (heap.get(rightChildPosition) > heap.get(leftChildPosition)) {
						maxChildPosition = rightChildPosition;
					}
				}
				if (heap.get(position) < heap.get(maxChildPosition)) {
					exchange(position, maxChildPosition);
					exchangeWithChildrenIfNeed(maxChildPosition);
				}
			}
		}

		private void exchangeWithParentIfNeed(int position) {
			int parentPosition = position / 2;
			if (parentPosition != 0) {
				Integer value = heap.get(position);
				Integer parentValue = heap.get(parentPosition);
				if (parentValue < value) {
					exchange(position, parentPosition);
					exchangeWithParentIfNeed(parentPosition);
				}
			}
		}

		private void exchange(int position1, int position2) {
			Integer value1 = heap.get(position1);
			Integer value2 = heap.get(position2);
			heap.set(position1, value2);
			heap.set(position2, value1);
			Integer index1 = heapPositionToIndex.get(position1);
			Integer index2 = heapPositionToIndex.get(position2);
			indexToHeapPosition.set(index1, position2);
			indexToHeapPosition.set(index2, position1);
			heapPositionToIndex.set(position1, index2);
			heapPositionToIndex.set(position2, index1);
		}

		public int top() {
			return heap.get(1);
		}
	}


}
