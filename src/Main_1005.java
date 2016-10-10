import utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Куча камней
 * http://acm.timus.ru/problem.aspx?space=1&num=1005
 */
public class Main_1005 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(reader.readLine());

		int[] stones = StreamUtils.readArray(count, new StreamTokenizer(reader));
		Arrays.sort(stones);

		int fullSum = 0;
		for (int i = 0; i < count; i++) {
			fullSum += stones[i];
		}

		int minDiff = fullSum;

		for (int size = 1; size <= count; size++) {
			List<Integer> subsums = subsums(size, stones, 0);

			for (Integer subsum : subsums) {
				minDiff = Math.min(minDiff, Math.abs(subsum - (fullSum - subsum)));
			}
		}

		System.out.println(minDiff);
	}

	private static List<Integer> subsums(int size, int[] array, int startIndex) {
		List<Integer> result = new ArrayList<>();
		if (size <= 0) {
			return result;
		}
		for (int i = startIndex; i <= array.length - size; i++) {
			if (size == 1) {
				result.add(array[i]);
				continue;
			}
			List<Integer> subsums = subsums(size - 1, array, i + 1);
			for (Integer subsum : subsums) {
				result.add(array[i] + subsum);
			}
		}
		return result;
	}
}
