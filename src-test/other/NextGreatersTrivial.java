package other;

public class NextGreatersTrivial {

	public static int[] nextGreaters(int[] array) {
		int[] result = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			result[i] = findNextGreater(array, i);
		}

		return result;
	}

	private static int findNextGreater(int[] array, int startPosition) {
		int value = array[startPosition];
		for (int i = startPosition + 1; i < array.length; i++) {
			if (array[i] > value) {
				return array[i];
			}
		}
		return -1;
	}
}
