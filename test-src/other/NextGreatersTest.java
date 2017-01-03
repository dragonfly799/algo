package other;

import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.AssertJUnit.*;

@Test
public class NextGreatersTest {

	private Random random = new Random();
	private static final int MAX_ARRAY_SIZE = 100000;
	private static final int MAX_ELEMENT = 1000000;

	public void testAscendingOrdered() {
		check(new int[]{0, 1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5, -1});
	}

	public void testDescendingOrdered() {
		check(new int[]{5, 4, 3, 2, 1, 0}, new int[]{-1, -1, -1, -1, -1, -1});
	}

	public void test() {
		check(new int[]{2, 5, 2, 0, 1, 3, 7}, new int[]{5, 7, 3, 1, 3, 7, -1});
	}

	@Test(invocationCount = 1000)
	public void testRandom() {
		int arraySize = random.nextInt(MAX_ARRAY_SIZE + 1);
		int[] input = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			input[i] = random.nextInt(MAX_ELEMENT);
		}
		check(input, NextGreatersTrivial.nextGreaters(input));
	}

	private void check(int[] input, int[] expectedOutput) {
		int[] result = NextGreaters.nextGreaters(input);
		assertArrayEquals(result, expectedOutput);
	}
}