package codeforces.round401;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.*;

@Test
public class Main_C_Test {

	private ByteArrayOutputStream out;

	@BeforeMethod
	public void setUp() {
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
	}

	@DataProvider(name = "files")
	public Object[][] dataProvider() {
		return new Object[][]{
				{"C/1"},
				{"C/2"},
//				{"C/3"},
		};

	}

	@Test(dataProvider = "files")
	public void test(String directory) {

		InputStream inputStream = getClass().getResourceAsStream(directory + "/input");
		System.setIn(inputStream);
		run();
		String expected = readFile(getClass().getResource(directory + "/output"));
		assertEquals(getOut(), expected);
	}

	private String readFile(URL inputStream) {
		try {
			Path path = Paths.get(inputStream.toURI());
			byte[] bytes = Files.readAllBytes(path);
			return new String(bytes);
		} catch (URISyntaxException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void run() {
		try {
			Main_C_Intervals.main(new String[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getOut() {
		try {
			return out.toString("UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}