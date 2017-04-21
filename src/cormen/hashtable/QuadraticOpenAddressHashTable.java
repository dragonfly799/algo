package cormen.hashtable;

public class QuadraticOpenAddressHashTable extends OpenAddressHashTable {

	private static final int C1 = 1;
	private static final int C2 = 2;

	public QuadraticOpenAddressHashTable(int capacity) {
		super(capacity);
	}

	@Override
	protected int hashCode(int key, int probe) {
		return (h(key) + C1 * probe + C2 * probe * probe) % capacity;
	}
}
