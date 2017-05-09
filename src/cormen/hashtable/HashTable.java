package cormen.hashtable;

public interface HashTable {
	int capacity();

	void put(int key, int value);

	Integer get(int key);

	void remove(int key);

}
