package cormen.hashtable;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class HashUtils {

	private static long s = (int) (((Math.sqrt(5) - 1) / 2) * (1L << 32));
	private static HashFunction murmur3_32 = Hashing.murmur3_32(17);

	public static long murmur3(int key) {
		int hash = murmur3_32.hashInt(key).asInt();
		return hash & 0xffffffffL;
	}

	public static int hash(int key, int bits) {
		int r0 = (int) ((key * s) & 0xffffffffL);
		return r0 >>> (32 - bits);
	}


}
