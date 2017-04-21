package cormen.hashtable;

import java.util.NoSuchElementException;

public class ChainedHashTable implements HashTable {

	private int capacity;
	private KeyChainElement[] table;

	public ChainedHashTable() {
		this(100);
	}

	public ChainedHashTable(int capacity) {
		this.capacity = capacity;
		table = new KeyChainElement[capacity];
	}

	@Override
	public void put(int key, int value) {
		int hash = hashCode(key);
		KeyChainElement element = new KeyChainElement(key, value);
		KeyChainElement next = table[hash];
		table[hash] = element;
		element.next = next;
	}


	@Override
	public Integer get(int key) {
		int hash = hashCode(key);
		KeyChainElement current = table[hash];
		while (current != null && current.key != key) {
			current = current.next;
		}
		if (current == null) {
			return null;
		}
		return current.value;
	}

	@Override
	public void remove(int key) {
		int hash = hashCode(key);

		KeyChainElement current = table[hash];
		KeyChainElement prev = null;
		while (current != null && current.key !=key) {
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

	private int hashCode(int key) {
		return (key >= 0) ? key % capacity : -key % capacity;
	}

	private static class KeyChainElement {
		int key;
		int value;
		KeyChainElement next;

		private KeyChainElement(int key, int value) {
			this.key = key;
			this.value = value;
		}

	}

}
