package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main_1019 {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(tokenizer);

		List<Point> points = new ArrayList<>();
		points.add(new Point(0, true));

		for (int i = 0; i < n; i++) {
			int a = readInt(tokenizer);
			int b = readInt(tokenizer);
			boolean white = readString(tokenizer).equals("w");

			Point aPoint = new Point(a, white);
			Point bPoint = new Point(b, !white);

			int pos = searchPosition(points, aPoint);
			if (pos == 0 || points.get(pos - 1).white != aPoint.white) {
				points.add(pos, aPoint);
				pos++;
			}

			while (pos < points.size() && points.get(pos).point <= bPoint.point) {
				points.remove(pos);
			}

			if (pos == points.size() && bPoint.white || pos < points.size() && points.get(pos).white != bPoint.white) {
				points.add(pos, bPoint);
			}

		}

		int maxLength = 0;
		int start = 0;
		int end = 0;
		for (int i = 0; i < points.size(); i += 2) {
			int currStart = points.get(i).point;
			int currEnd = i + 1 < points.size() ? points.get(i + 1).point : 1000000000;
			if (currEnd - currStart > maxLength) {
				maxLength = currEnd - currStart;
				start = currStart;
				end = currEnd;
			}
		}

		System.out.println(start + " " + end);
	}

	private static int searchPosition(List<Point> points, Point target) {
		int left = 0;
		int right = points.size() - 1;
		while (left <= right) {
			int i = (left + right) / 2;
			if (points.get(i).point >= target.point && (i == 0 || points.get(i - 1).point < target.point)) {
				return i;
			}

			if (points.get(i).point >= target.point) {
				right = i - 1;
			} else {
				left = i + 1;
			}
		}

		return left;
	}

	private static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static String readString(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return tokenizer.sval;
	}

	private static class Point {
		int point;
		boolean white;

		private Point(int point, boolean white) {
			this.point = point;
			this.white = white;
		}

		@Override
		public String toString() {
			return "[" + point + ", " + (white ? "w" : "b") + "]";
		}
	}
}
