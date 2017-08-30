package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_SearchInsertPosition {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int size = readInt(tokenizer);
		int[] array = readArray(size, tokenizer);

		while (true) {
			int num = readInt(tokenizer);
			System.out.println(searchInsert(array, num));
		}
	}

	public static int searchInsert(int[] nums, int target) {
		int leftBound = 0;
		int rightBound = nums.length - 1;

		while (true) {
			int i = (leftBound + rightBound) / 2;
			if (target == nums[i]) {
				return i;
			}

			if (target < nums[i]) {
				rightBound = i - 1;
			} else {
				leftBound = i + 1;
			}

			if (leftBound > rightBound) {
				return leftBound;
			}
		}
	}

	private static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static int[] readArray(int size, StreamTokenizer tokenizer) throws IOException {
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[i] = readInt(tokenizer);
		}
		return result;
	}

}
