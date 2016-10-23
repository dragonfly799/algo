package leetcode;

import utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_RotatedArray {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int size = StreamUtils.readInt(tokenizer);
		int[] array = StreamUtils.readArray(size, tokenizer);

		while (true) {
			int target = StreamUtils.readInt(tokenizer);
			System.out.println(search(array, target));
		}
	}

	public static int search(int[] nums, int target) {
		int rotation = findRotationPoint(nums);
		System.out.println("rotation = " + rotation);
		if (rotation == -1) {
			return search(nums, target, 0, nums.length - 1);
		} else if (target >= nums[0]) {
			return search(nums, target, 0, rotation - 1);
		} else {
			return search(nums, target, rotation, nums.length - 1);
		}
	}

	private static int search(int[] nums, int target, int leftBound, int rightBound) {
		while (true) {
			int i = (leftBound + rightBound) / 2;
			if (nums[i] == target) {
				return i;
			}
			if (nums[i] < target) {
				leftBound = i + 1;
			} else {
				rightBound = i - 1;
			}
			if (leftBound > rightBound) {
				return -1;
			}
		}
	}

	private static int findRotationPoint(int[] nums) {
		int leftBound = 0;
		int rightBound = nums.length - 1;
		if (nums[leftBound] < nums[rightBound]) {
			return -1;
		}
		while (true) {
			int i = (leftBound + rightBound) / 2;
			if (i == 0) {
				if (leftBound == rightBound) return i;
				if (nums[i] < nums[i + 1]) return i;
				else return i + 1;
			}
			if (nums[i] < nums[i - 1]) {
				return i;
			}
			if (nums[i] > nums[rightBound]) {
				leftBound = i + 1;
			} else {
				rightBound = i - 1;
			}

			if (leftBound > rightBound) {
				return -1;
			}
		}
	}

}
