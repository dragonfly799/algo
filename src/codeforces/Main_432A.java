import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_432A {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(tokenizer);
		int k = readInt(tokenizer);

		int[] students = readArray(n, tokenizer);
		Arrays.sort(students);

		System.out.println(search(students, n, 5 - k) / 3);
	}

	private static int search(int[] array, int size, int maxValue) {
		int left = 0;
		int right = size - 1;
		while (left <= right) {
			int i = (left + right) / 2;
			if (array[i] <= maxValue && (i == size - 1 || array[i + 1] > maxValue)) {
				return i + 1;
			}
			if (array[i] > maxValue) {
				right = i - 1;
			} else {
				left = i + 1;
			}
		}
		return 0;
	}

	public static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	public static int[] readArray(int size, StreamTokenizer tokenizer) throws IOException {
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = readInt(tokenizer);
		}
		return result;
	}

}
