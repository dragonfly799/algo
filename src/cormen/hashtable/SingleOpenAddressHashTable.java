package cormen.hashtable;

public class SingleOpenAddressHashTable extends OpenAddressHashTable {

	public SingleOpenAddressHashTable(int capacity) {
		super(capacity);
	}

	@Override
	protected int hashCode(int key, int probe) {
		return (h(key) + probe) % capacity;
	}
}
