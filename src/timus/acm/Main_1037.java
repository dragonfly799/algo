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
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

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
		out.flush();
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
		private ArrayList<Integer> heap = new ArrayList<>(SIZE + 1);

		private FreeBlocks() {
			for (int i = 0; i <= SIZE; i++) {
				heap.add(i);
			}
		}

		private void free(int blockNumber) {
			heap.add(blockNumber);
			exchangeWithParentIfNeed(heap.size() - 1);
		}

		private int allocate() {
			int allocated = heap.get(1);
			int lastPosition = heap.size() - 1;
			Integer lastValue = heap.remove(lastPosition);
			if (lastPosition > 1) {
				heap.set(1, lastValue);
				exchangeWithChildrenIfNeed(1);
			}
			return allocated;
		}

		private void exchangeWithParentIfNeed(int position) {
			int parentPosition = position / 2;
			if (parentPosition != 0) {
				Integer value = heap.get(position);
				Integer parentValue = heap.get(parentPosition);
				if (parentValue > value) {
					exchange(position, parentPosition);
					exchangeWithParentIfNeed(parentPosition);
				}
			}
		}

		private void exchangeWithChildrenIfNeed(int position) {
			int leftChildPosition = position * 2;
			int rightChildPosition = leftChildPosition + 1;
			if (leftChildPosition < heap.size()) {
				int minChildPosition = leftChildPosition;
				if (rightChildPosition < heap.size()) {
					if (heap.get(rightChildPosition) < heap.get(leftChildPosition)) {
						minChildPosition = rightChildPosition;
					}
				}
				if (heap.get(position) > heap.get(minChildPosition)) {
					exchange(position, minChildPosition);
					exchangeWithChildrenIfNeed(minChildPosition);
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