package cormen.hashtable;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.testng.Assert.*;

@Test
public class HashTableTest {

	private HashTable<Integer, Integer> hashTable;

	@BeforeMethod
	public void setUp() {
		hashTable = new ChainedHashTable<>();
	}

	public void test() {

		Integer key = 1;
		Integer value = 100;
		hashTable.put(key, value);

		Integer get = hashTable.get(key);
		assertEquals(get, value);

		hashTable.remove(key);
		get = hashTable.get(key);
		assertNull(get);
	}

	public void randomTest() {
		Map<Integer, Integer> elements = new HashMap<>();
		Random random = new Random();

		for (int key = 0; key < 200; key++) {
			int value = random.nextInt();
			elements.put(key, value);
			hashTable.put(key, value);
		}

		elements.keySet().forEach(k -> {
			Integer search = hashTable.get(k);
			assertNotNull(search);
			assertEquals(search, elements.get(k));
		});

		elements.keySet().forEach(k -> {
			hashTable.remove(k);
			assertNull(hashTable.get(k));
		});
	}



}