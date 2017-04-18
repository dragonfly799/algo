package timus.acm;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.testng.Assert.*;

@Test
public class Main_1028Test {

	private ByteArrayOutputStream out;
	private Random random = new Random();

	@BeforeMethod
	public void setUp() {
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	public void testHorizontalLine() {
		setIn(new String[]{"1 1", "2 1", "3 1", "4 1", "5 1"});
		run();
		assertEquals(getOut(), new String[]{"codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1"});
	}

	public void testVerticalLine() {
		setIn(new String[]{"1 1", "1 2", "1 3", "1 4", "1 5"});
		run();
		assertEquals(getOut(), new String[]{"codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1"});
	}

	public void testMainDiagonal() {
		setIn(new String[]{"1 1", "2 2", "3 3", "4 4", "5 5"});
		run();
		assertEquals(getOut(), new String[]{"codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1", "codeforces/round401/1"});
	}

	public void testSecondaryDiagonal() {
		setIn(new String[]{"5 1", "4 2", "3 3", "2 4", "1 5"});
		run();
		assertEquals(getOut(), new String[]{"5", "0", "0", "0", "0"});
	}

	public void testSquareNine() {
		setIn(new String[]{"0 0", "1 0", "2 0", "0 1", "1 1", "2 1", "0 2", "1 2", "2 2"});
		run();
		assertEquals(getOut(), new String[]{"codeforces/round401/1", "2", "2", "codeforces/round401/1", "0", "2", "0", "0", "codeforces/round401/1"});
	}

	public void testRhombusFour() {
		setIn(new String[]{"1 0", "0 1", "2 1", "1 2"});
		run();
		assertEquals(getOut(), new String[]{"2", "0", "2", "0"});
	}

	public void testSinglePoint() {
		setIn(new String[]{"0 0"});
		run();
		assertEquals(getOut(), new String[]{"codeforces/round401/1"});
	}

	public void testTwoDiagonals() {
		setIn(new String[]{"8 0", "6 1", "9 1", "4 2", "7 2", "2 3", "5 3", "0 4", "3 4", "1 5"});
		run();
		assertEquals(getOut(), new String[]{"5", "codeforces/round401/1", "4", "0", "0", "0", "0", "0", "0", "0"});
	}

	@Test(invocationCount = 100, timeOut = 250)
	public void test() {
		String[] input = new String[15000];
		for (int i = 0; i < 15000; i++) {
			input[i] = "" + random.nextInt(32001) + " " + i;
		}
		setIn(input);
		run();
	}

	private void run() {
		try {
			Main_1028_Intervals.main(new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setIn(String[] strings) {
		StringBuilder builder = new StringBuilder();
		builder.append(strings.length).append("\n");
		for (String string : strings) {
			builder.append(string).append("\n");
		}
		System.setIn(new ByteArrayInputStream(builder.toString().getBytes(StandardCharsets.UTF_8)));
	}

	private String[] getOut() {
		try {
			return out.toString("UTF8").split("\n");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}