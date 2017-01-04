package timus.acm;

import java.io.*;
import java.util.*;

public class Main_1037 {

	private static final int TIME_LIMIT = 600;
	private static final int SIZE = 30000;

	private static AllocatedBlock[] numberToBlock = new AllocatedBlock[SIZE + 1];
	private static Deque<AllocatedBlock> allocated = new LinkedList<>();
	private static FreeBlocks freeBlocks = new FreeBlocks();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintStream out = System.out;

		do {
			String line = reader.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			int time = Integer.parseInt(tokenizer.nextToken());

			if (tokenizer.nextToken().equals("+")) {
				int allocatedBlockNumber = allocate(time);
				out.println(allocatedBlockNumber);
			} else {
				int blockNumber = Integer.parseInt(tokenizer.nextToken());
				boolean successful = access(time, blockNumber);
				out.println(successful ? "+" : "-");
			}
		} while (reader.ready());

	}

	private static int allocate(int time) {
		AllocatedBlock first = allocated.peekFirst();
		while (first != null && first.time <= time - TIME_LIMIT) {
			free(first);
			first = allocated.peekFirst();
		}

		int blockNumber = freeBlocks.allocate();
		AllocatedBlock block = new AllocatedBlock(time, blockNumber);
		numberToBlock[blockNumber] = block;
		allocated.addLast(block);
		return blockNumber;
	}

	private static void free(AllocatedBlock first) {
		allocated.pollFirst();
		numberToBlock[first.number] = null;
		freeBlocks.free(first.number);
	}

	private static boolean access(int time, int block) {
		AllocatedBlock allocatedBlock = numberToBlock[block];
		if (allocatedBlock == null) {
			return false;
		}
		if (allocatedBlock.time <= time - TIME_LIMIT) {
			free(allocatedBlock);
			return false;
		}
		allocatedBlock.time = time;
		allocated.remove(allocatedBlock);
		allocated.addLast(allocatedBlock);
		return true;
	}

	private static class AllocatedBlock {
		private AllocatedBlock(int time, int number) {
			this.time = time;
			this.number = number;
		}

		private int time;
		private int number;
	}

	private static class FreeBlocks {
		private SortedSet<Integer> blocks = new TreeSet<>();

		public FreeBlocks() {
			for (int i = 1; i <= SIZE; i++) {
				blocks.add(i);
			}
		}

		public int allocate() {
			Integer first = blocks.first();
			blocks.remove(first);
			return first;
		}

		public void free(int number) {
			blocks.add(number);
		}
	}

	private static class FreeBlocksHeap {
		private int[] heap = new int[SIZE + 1];
		private int lastPosition;

		private FreeBlocksHeap() {
			for (int i = 1; i <= SIZE; i++) {
				heap[i] = i;
			}
			lastPosition = SIZE;
		}

		private void free(int blockNumber) {
			lastPosition++;
			heap[lastPosition] = blockNumber;
			exchangeWithParentIfNeed(lastPosition);
		}

		private int allocate() {
			if (lastPosition == 0) {
				throw new IndexOutOfBoundsException("Heap is empty");
			}
			int allocated = heap[1];
			Integer lastValue = heap[lastPosition];
			lastPosition--;
			if (lastPosition > 1) {
				heap[1] = lastValue;
				exchangeWithChildrenIfNeed(1);
			}
			return allocated;
		}

		private void exchangeWithParentIfNeed(int position) {
			int parentPosition = position / 2;
			if (parentPosition != 0) {
				Integer value = heap[position];
				Integer parentValue = heap[parentPosition];
				if (parentValue > value) {
					exchange(position, parentPosition);
					exchangeWithParentIfNeed(parentPosition);
				}
			}
		}

		private void exchangeWithChildrenIfNeed(int position) {
			int leftChildPosition = position * 2;
			int rightChildPosition = leftChildPosition + 1;
			if (leftChildPosition <= lastPosition) {
				int minChildPosition = leftChildPosition;
				if (rightChildPosition <= lastPosition) {
					if (heap[rightChildPosition] < heap[leftChildPosition]) {
						minChildPosition = rightChildPosition;
					}
				}
				if (heap[position] > heap[minChildPosition]) {
					exchange(position, minChildPosition);
					exchangeWithChildrenIfNeed(minChildPosition);
				}
			}
		}

		private void exchange(int position1, int position2) {
			Integer value1 = heap[position1];
			Integer value2 = heap[position2];
			heap[position1] = value2;
			heap[position2] = value1;
		}
	}
}
