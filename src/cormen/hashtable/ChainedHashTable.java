package cormen.hashtable;

import java.util.NoSuchElementException;

public class ChainedHashTable<K, V> implements HashTable<K, V> {

	private int capacity;
	private KeyChainElement<K, V>[] table;

	public ChainedHashTable() {
		this(100);
	}

	public ChainedHashTable(int capacity) {
		this.capacity = capacity;
		table = new KeyChainElement[capacity];
	}

	@Override
	public void put(K key, V value) {
		int hash = hash(key);
		KeyChainElement<K, V> element = new KeyChainElement<>(key, value);
		KeyChainElement<K, V> next = table[hash];
		table[hash] = element;
		element.next = next;
	}


	@Override
	public V get(K key) {
		int hash = hash(key);
		KeyChainElement<K, V> current = table[hash];
		while (current != null && !current.key.equals(key)) {
			current = current.next;
		}
		if (current == null) {
			return null;
		}
		return current.value;
	}

	@Override
	public void remove(K key) {
		int hash = hash(key);

		KeyChainElement<K, V> current = table[hash];
		KeyChainElement<K, V> prev = null;
		while (current != null && !current.key.equals(key)) {
			prev = current;
			current = current.next;
		}
		if (current == null) {
			throw new NoSuchElementException("key=" + key);
		}
		if (prev == null) {
			table[hash] = current.next;
		} else {
			prev.next = current.next;
		}
	}

	private int hash(K key) {
		int hashCode = key.hashCode();
		return (hashCode >= 0) ? hashCode % capacity : -hashCode % capacity;
	}

	private static class KeyChainElement<K, V> {
		K key;
		V value;
		KeyChainElement<K, V> next;

		private KeyChainElement(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}

}
