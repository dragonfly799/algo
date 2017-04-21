package cormen.hashtable;

public abstract class OpenAddressHashTable implements HashTable {

	private static final Element REMOVED = new Element();
	protected int capacity;
	private Element[] table;

	public OpenAddressHashTable(int capacity) {
		this.capacity = capacity;
		table = new Element[capacity];
	}

	@Override
	public void put(int key, int value) {
		for (int probe = 0; probe < capacity; probe++) {
			int index = hashCode(key, probe);
			if (table[index] == null || table[index] == REMOVED) {
				table[index] = new Element(key, value);
				break;
			}
		}
	}

	@Override
	public Integer get(int key) {
		for (int probe = 0; probe < capacity; probe++) {
			int index = hashCode(key, probe);
			Element element = table[index];
			if (element == null) {
				return null;
			}
			if (element.key == key) {
				return element.value;
			}
		}
		return null;
	}

	@Override
	public void remove(int key) {
		for (int probe = 0; probe < capacity; probe++) {
			int index = hashCode(key, probe);
			Element element = table[index];
			if (element == null) {
				break;
			}
			if (element.key == key) {
				table[index] = REMOVED;
			}
		}
	}

	protected abstract int hashCode(int key, int probe);

	protected int h(int key) {
		return key;
	}

	private static class Element {
		int key;
		int value;

		private Element() {
		}

		private Element(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}
