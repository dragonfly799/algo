package cormen.hashtable;

public class SingleOpenAddressHashTable extends OpenAddressHashTable {

	public SingleOpenAddressHashTable(int capacity) {
		super(capacity);
	}

	@Override
	protected int hashCode(long h, int probe) {
		return (int) ((h + probe) % capacity);
	}
}
