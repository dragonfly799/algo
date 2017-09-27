package timus.acm;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

@Test
public class Main_1635Test {

	public void testSingleChar() {
		String line = "a";
		check(line, 1);
	}

	public void testOddSingleCharString() {
		String line = "aaaaa";
		check(line, 1);
	}

	public void testEvenSingleCharString() {
		String line = "aaaaaa";
		check(line, 1);
	}

	public void testNoPal() {
		String line = "pasoib";
		check(line, 6);
	}

	public void testTwoPals() {
		String line = "abcbaqweewq";
		check(line, 2);
	}

	public void testThreePals() {
		String line = "zzzqxx";
		check(line, 3);
	}

	public void testFullPalOdd() {
		String line = "wasitacatisaw";
		check(line, 1);
	}

	public void testFullPalEven() {
		String line = "wasitaccatisaw";
		check(line, 1);
	}

	public void test1() {
		String line = "ababab";
		check(line, 2);
	}

	public void test2() {
		String line = "abbabac";
		check(line, 4);
	}

	public void test3() {
		String line = "abaaba";
		check(line, 1);
	}

	@Test(timeOut = 100)
	public void testLong() {
		StringBuilder sb = new StringBuilder();
		String alphabet = "aba";
		for (int i = 0; i < 4000; i++) {
			sb.append(alphabet.charAt(i % alphabet.length()));
		}
		String line = sb.toString();
		check(line, 2);
	}

	@Test(invocationCount = 1000)
	public void testRandom() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		String alphabet = "qwertyuiopasdfghjklzxcvbnm";
		for (int i = 0; i < 4000; i++) {
			int r = random.nextInt(alphabet.length());
			sb.append(alphabet.charAt(r));
		}
		String line = sb.toString();

		int actualCount = Main_1635_Manacher.find(line);
		List<String> actualStrings = getStrings(line, Main_1635_Manacher.nextIndexCache);

		int expectedCount = Main_1635.find(line);
		List<String> expectedStrings = getStrings(line, Main_1635.nextIndexCache);

		String s = "\nactual:   " + actualStrings
				+ "\nexpected: " + expectedStrings;
		assertEquals(actualCount, expectedCount, s);
	}

	private void check(String line, int count) {
		int actualCount = Main_1635_Manacher.find(line);
		List<String> strings = getStrings(line, Main_1635_Manacher.nextIndexCache);

		System.out.println("Main_1635Test.check " + strings);

		assertEquals(actualCount, count);
	}

	private List<String> getStrings(String line, int[] cache) {
		List<String> strings = new ArrayList<>();

		int start = 0;
		while (start >= 0 && start < line.length()) {
			StringBuilder sb = new StringBuilder();
			int next = cache[start];
			for (int i = start; i < next; i++) {
				sb.append(line.charAt(i));
			}
			strings.add(sb.toString());
			start = next;
		}
		return strings;
	}
}