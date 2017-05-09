package cormen.hashtable;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;

public class HashTableBenchmark {

	private static final Random random = new Random();

	public static void main(String[] args) {
		int insertions = 1_000_000;

		System.out.printf("\n\nChained hash table\n\n");
		printHeader();
		test(() -> new ChainedHashTable(10_000), insertions);
		test(() -> new ChainedHashTable(20_000), insertions);
		test(() -> new ChainedHashTable(100_000), insertions);
		test(() -> new ChainedHashTable(200_000), insertions);
		test(() -> new ChainedHashTable(1_000_000), insertions);

		final int capacity = 1 << 20;
		System.out.printf("\n\nOpen address (single hash)\n\n");
		printHeader();
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.97));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.95));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.93));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.90));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.85));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.80));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.70));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.60));
		test(() -> new SingleOpenAddressHashTable(capacity), (int) (capacity * 0.50));

		System.out.printf("\n\nOpen address (quadratic hash)\n\n");
		printHeader();
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.97));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.95));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.93));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.90));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.85));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.80));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.70));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.60));
		test(() -> new QuadraticOpenAddressHashTable(capacity), (int) (capacity * 0.50));
	}

	private static void test(Callable<HashTable> hashTableFactory, int insertions) {
		try {
			HashTable hashTable = hashTableFactory.call();
			int capacity = hashTable.capacity();
			HashMap<Integer, Integer> storedValues = new HashMap<>();
			for (int i = 0; i < insertions; i++) {
				int key = random.nextInt();
				while (storedValues.containsKey(key)) {
					key = random.nextInt();
				}
				int value = random.nextInt();
				storedValues.put(key, value);
			}

			long fillTime = fill(hashTable, storedValues);
			long selectTime = select(hashTable, storedValues);
			System.out.printf(" %10d | %10d | %11.2f | %18d ms | %20d ms \n",
					capacity, insertions, (double) insertions / capacity, fillTime * 1_000_000 / insertions, selectTime * 1_000_000 / insertions);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void printHeader() {
		System.out.printf("   Capacity | Insertions | Load factor | Fill time (per 1 mln) | Select time (per 1 mln) \n");
		System.out.printf("-----------------------------------------------------------------------------------------\n");
	}

	private static long fill(HashTable hashTable, HashMap<Integer, Integer> storedValues) {
		long start = System.currentTimeMillis();
		storedValues.forEach((k, v) -> hashTable.put(k, v));
		return System.currentTimeMillis() - start;
	}

	private static long select(HashTable hashTable, HashMap<Integer, Integer> storedValues) {
		long start = System.currentTimeMillis();
		storedValues.forEach((k, v) -> {
			Integer value = hashTable.get(k);
			if (!v.equals(value)) {
				throw new RuntimeException();
			}
		});
		return System.currentTimeMillis() - start;
	}

}
