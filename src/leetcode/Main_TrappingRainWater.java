package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_TrappingRainWater {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(in);

		int[] heights = new int[n];
		for (int i = 0; i < n; i++) {
			heights[i] = readInt(in);
		}
		System.out.println(trap(heights));
	}

	public static int trap(int[] height) {
		int trapped = 0;

		int leftTop = -1;
		int rightTop = -1;

		int l = 0;
		int r = height.length - 1;

		int leftTopIndex = 0;
		int rightTopIndex = height.length - 1;

		while (leftTopIndex < rightTopIndex) {
			if (height[l] >= leftTop) {
				trapped += calcTrapped(height, leftTopIndex, l);
				leftTopIndex = l;
				leftTop = height[l];
				l++;
			} else if (height[r] >= rightTop) {
				trapped += calcTrapped(height, r, rightTopIndex);
				rightTopIndex = r;
				rightTop = height[r];
				r--;
			} else {
				l++;
				r--;
			}
		}

		return trapped;
	}

	private static int calcTrapped(int[] height, int leftTop, int rightTop) {
		int res = 0;
		int waterline = Math.min(height[leftTop], height[rightTop]);
		for (int i = leftTop + 1; i < rightTop; i++) {
			res += waterline - height[i];
		}

		return res;
	}

	private static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

}
