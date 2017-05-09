package cormen.hashtable;

public class QuadraticOpenAddressHashTable extends OpenAddressHashTable {

	private static final double C1 = 0.5;
	private static final double C2 = 0.5;

	public QuadraticOpenAddressHashTable(int capacity) {
		super(capacity);
	}

	@Override
	protected int hashCode(long h, int probe) {
		return (int) ((h + probe * C1 + (long) probe * probe * C2) % capacity);
	}
}
