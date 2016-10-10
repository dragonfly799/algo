import utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Куча камней
 * http://acm.timus.ru/problem.aspx?space=1&num=1005
 */
public class Main_1005 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(reader.readLine());

		int[] stones = StreamUtils.readArray(count, new StreamTokenizer(reader));

		int fullSum = 0;
		for (int i = 0; i < count; i++) {
			fullSum += stones[i];
		}

		int minDiff = fullSum;

		for (int i = 0; i < 1 << count - 1; i++) {
			int sum = sum(stones, i);
			minDiff = Math.min(minDiff, Math.abs(sum - (fullSum - sum)));
		}

		System.out.println(minDiff);
	}

	private static int sum(int[] array, int bitmask) {
		int sum = 0;
		for (int i = 0; i < array.length - 1; i++) {
			if ((bitmask & (1 << i)) != 0) {
				sum += array[i];
			}
		}
		return sum;
	}

}
