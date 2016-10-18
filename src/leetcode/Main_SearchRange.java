package leetcode;

import utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_SearchRange {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int size = StreamUtils.readInt(tokenizer);
		int[] array = StreamUtils.readArray(size, tokenizer);

		while (true) {
			int num = StreamUtils.readInt(tokenizer);
			System.out.println(Arrays.toString(searchRange(array, num)));
		}
	}

	public static int[] searchRange(int[] nums, int target) {
		int leftBound = findLeftBound(nums, target);
		if (leftBound == -1) {
			return new int[]{-1, -1};
		} else {
			return new int[]{leftBound, findRightBound(nums, target)};
		}
	}

	private static int findLeftBound(int[] array, int target) {
		int leftBound = 0;
		int rightBound = array.length - 1;

		while (true) {
			int i = (leftBound + rightBound) / 2;

			if (array[i] == target && (i == leftBound || array[i - 1] != target)) {
				return i;
			}

			if (array[i] >= target) {
				rightBound = i - 1;
			} else {
				leftBound = i + 1;
			}
			if (leftBound > rightBound) {
				return -1;
			}
		}
	}

	private static int findRightBound(int[] array, int target) {
		int leftBound = 0;
		int rightBound = array.length - 1;

		while (true) {
			int i = (leftBound + rightBound) / 2;

			if (array[i] == target && (i == rightBound || array[i + 1] != target)) {
				return i;
			}

			if (array[i] > target) {
				rightBound = i - 1;
			} else {
				leftBound = i + 1;
			}
			if (leftBound > rightBound) {
				return -1;
			}
		}
	}
}
