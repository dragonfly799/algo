package cormen.hashtable;

public abstract class OpenAddressHashTable implements HashTable {

	private static final Element REMOVED = new Element(-1, -1);

	protected final int capacity;
	private Element[] table;
	private int p;

	private long s = (int) (((Math.sqrt(5) - 1) / 2) * (1L << 32));

	protected OpenAddressHashTable(int capacity) {
		int p = 0;
		while (1 << p < capacity) {
			p++;
		}
		this.p = p;
		this.capacity = 1 << p;
		table = new Element[this.capacity];
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public void put(int key, int value) {
		long h = h(key);
		for (int probe = 0; probe < capacity; probe++) {
			int index = hashCode(h, probe);
			if (table[index] == null || table[index] == REMOVED) {
				table[index] = new Element(key, value);
				return;
			}
			if (table[index].key == key) {
				table[index].value = value;
				return;
			}
		}
		throw new RuntimeException("Cannot insert value: key=" + key + ". h=" + h + ", table=" + this);
	}

	@Override
	public Integer get(int key) {
		long h = h(key);
		for (int probe = 0; probe < capacity; probe++) {
			int index = hashCode(h, probe);
			Element element = table[index];
			if (element == null) {
				return null;
			}
			if (element == REMOVED) {
				continue;
			}
			if (element.key == key) {
				return element.value;
			}
		}
		return null;
	}

	@Override
	public void remove(int key) {
		long h = h(key);
		for (int probe = 0; probe < capacity; probe++) {
			int index = hashCode(h, probe);
			Element element = table[index];
			if (element == null) {
				return;
			}
			if (element.key == key) {
				table[index] = REMOVED;
				return;
			}
		}
		throw new RuntimeException("Cannot remove value: key=" + key + ". h=" + h + ", table=" + this);
	}

	protected abstract int hashCode(long key, int probe);

	protected int h(int key) {
		int r0 = (int) ((key * s) & 0xffffffffL);
		return r0 >>> (32 - p);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder(getClass().getSimpleName()).append("[");
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null)
				res.append("\n\t").append(table[i].key).append(" = ").append(table[i].value);
		}
		res.append("]");
		return res.toString();
	}

	private static class Element {
		int key;
		int value;

		private Element(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
}