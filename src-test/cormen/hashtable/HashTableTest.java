package cormen.hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.testng.Assert.*;

@Test
public class HashTableTest {

	@DataProvider(name = "hashes")
	public Object[][] createData() {
		return new Object[][]{
				{new ChainedHashTable(10)},
				{new SingleOpenAddressHashTable(16)},
				{new QuadraticOpenAddressHashTable(16)}
		};
	}

	@Test(dataProvider = "hashes")
	public void returnValue_whenStored(HashTable hashTable) {
		Integer key = 1;
		Integer value = 100;
		hashTable.put(key, value);

		Integer get = hashTable.get(key);
		assertEquals(get, value);
	}

	@Test(dataProvider = "hashes")
	public void returnNull_whenNotStored(HashTable hashTable) {
		assertNull(hashTable.get(1));
	}

	@Test(dataProvider = "hashes")
	public void returnNull_whenStoredAndRemoved(HashTable hashTable) {
		Integer key = 1;
		Integer value = 100;
		hashTable.put(key, value);

		hashTable.remove(1);

		assertNull(hashTable.get(1));
	}

	@Test(dataProvider = "hashes")
	public void returnNewValue_whenStoredTwice(HashTable hashTable) {
		Integer key = 1;
		hashTable.put(key, 100);
		Integer newValue = 200;
		hashTable.put(key, newValue);

		assertEquals(hashTable.get(1), newValue);
	}

	@Test(dataProvider = "hashes")
	public void returnNullValue_whenStoredTwiceAndRemoved(HashTable hashTable) {
		Integer key = 1;
		hashTable.put(key, 100);
		hashTable.put(key, 200);

		hashTable.remove(1);

		assertNull(hashTable.get(1));
	}

	@Test(dataProvider = "hashes", invocationCount = 10)
	public void randomTest(HashTable hashTable) {
		Map<Integer, Integer> elements = new HashMap<>();
		Random random = new Random();

		for (int i = 0; i < 16; i++) {
			int key = random.nextInt();
			int value = random.nextInt();
			hashTable.put(key, value);
			elements.put(key, value);
		}

		elements.keySet().forEach(key -> {
			int value = random.nextInt();
			elements.put(key, value);
			hashTable.put(key, value);

		});

		elements.keySet().forEach(k -> {
			Integer search = hashTable.get(k);
			assertNotNull(search);
			assertEquals(search, elements.get(k));
		});

		for (int i = 0; i < 10000; i++) {
			Integer key = random.nextInt();
			if (elements.containsKey(key)) {
				assertEquals(key, hashTable.get(key));
			} else {
				assertNull(hashTable.get(key));
			}
		}

		elements.keySet().forEach(k -> {
			hashTable.remove(k);
			assertNull(hashTable.get(k));
		});
	}
}