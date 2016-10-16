package leetcode;

import utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_SearchInsertPosition {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int size = StreamUtils.readInt(tokenizer);
		int[] array = StreamUtils.readArray(size, tokenizer);

		while (true) {
			int num = StreamUtils.readInt(tokenizer);
			System.out.println(searchInsert(array, num));
		}
	}

	public static int searchInsert(int[] nums, int target) {
		int leftBound = 0;
		int rightBound = nums.length - 1;

		while (true) {
			int i = (leftBound + rightBound) / 2;
			if (target ==  nums[i]) {
				return i;
			}

			if (target <  nums[i]) {
				rightBound = i - 1;
			} else {
				leftBound = i + 1;
			}

			if (leftBound > rightBound) {
				return leftBound;
			}
		}
	}
}
