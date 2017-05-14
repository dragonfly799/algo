package cormen.hashtable;

import java.util.NoSuchElementException;

public class ChainedHashTable implements HashTable {

	private KeyChainElement[] table;
	private int capacity;
	private int p;


	public ChainedHashTable(int capacity) {
		int p = 0;
		while (1 << p < capacity) {
			p++;
		}
		this.p = p;
		this.capacity = 1 << p;
		table = new KeyChainElement[this.capacity];
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public void put(int key, int value) {
		int hash = hashCode(key);
		KeyChainElement current = table[hash];
		KeyChainElement prev = null;
		while (current != null && current.key != key) {
			prev = current;
			current = current.next;
		}
		if (current == null) {
			KeyChainElement element = new KeyChainElement(key, value);
			if (prev == null) {
				table[hash] = element;
			} else {
				prev.next = element;
			}
		} else {
			current.value = value;
		}
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
		while (current != null && current.key != key) {
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
		return (int) (HashUtils.murmur3(key) % capacity);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder(getClass().getSimpleName()).append("[");
		for (int i = 0; i < capacity; i++) {
			KeyChainElement element = table[i];
			while (element != null) {
				res.append("\n\t").append(element.key).append(" = ").append(element.value);
				element = element.next;
			}
		}
		res.append("]");
		return res.toString();
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
