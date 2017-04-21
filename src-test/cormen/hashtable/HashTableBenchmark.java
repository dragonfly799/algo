package cormen.hashtable;

import java.util.Random;

public class HashTableBenchmark {

	private static Random random = new Random();

	public static void main(String[] args) {
		HashTable hashTable = new ChainedHashTable(100_000);
		int size = 1_000_000;
		int iterations = 1_000_000;

		fill(hashTable, size);
		select(hashTable, iterations, size);
	}

	private static long fill(HashTable hashTable, int size) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			hashTable.put(i, random.nextInt());
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("HashTableBenchmark.fill " + time + " ms");
		return time;
	}

	private static long select(HashTable hashTable, int iterations, int size) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			int key = random.nextInt(size);
			hashTable.get(key);
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("HashTableBenchmark.select " + time + " ms");
		return time;
	}

}
