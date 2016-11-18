package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main_1019_Scan {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(tokenizer);

		List<Point> points = new ArrayList<>();
		Interval interval = new Interval(0, true);
		points.add(new Point(0, true, interval));
		points.add(new Point(1000000000, false, interval));

		for (int i = 1; i <= n; i++) {
			int a = readInt(tokenizer);
			int b = readInt(tokenizer);
			boolean white = readString(tokenizer).equals("w");

			interval = new Interval(i, white);
			Point start = new Point(a, true, interval);
			Point end = new Point(b, false, interval);
			points.add(searchPosition(points, start), start);
			points.add(searchPosition(points, end), end);
		}

		SortedSet<Interval> currentIntervals = new TreeSet<>();
		int start = 0;
		int end = 0;
		int maxLength = 0;

		int currStart = 0;
		int currEnd = 0;
		boolean white = true;

		Iterator<Point> iterator = points.iterator();
		Point point = iterator.next();

		while (iterator.hasNext()) {
			int p = point.point;
			while (point.point == p) {
				if (point.start) {
					currentIntervals.add(point.interval);
				} else {
					currentIntervals.remove(point.interval);
				}
				if (!iterator.hasNext()) {
					break;
				}
				point = iterator.next();
			}

			boolean lastWhite = currentIntervals.isEmpty() || currentIntervals.last().white;
			if (white != lastWhite) {
				if (white) {
					currEnd = p;
					if (currEnd - currStart > maxLength) {
						maxLength = currEnd - currStart;
						start = currStart;
						end = currEnd;
					}
				} else {
					currStart = p;
				}
				white = lastWhite;
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
		boolean start;
		Interval interval;

		private Point(int point, boolean start, Interval interval) {
			this.point = point;
			this.start = start;
			this.interval = interval;
		}

		@Override
		public String toString() {
			return "[" + point + ", "
					+ (start ? "start" : "end") + ", "
					+ interval + "]";
		}
	}

	private static class Interval implements Comparable<Interval> {
		int intervalNumber;
		boolean white;

		private Interval(int intervalNumber, boolean white) {
			this.intervalNumber = intervalNumber;
			this.white = white;
		}

		@Override
		public int compareTo(Interval o) {
			return intervalNumber - o.intervalNumber;
		}

		@Override
		public String toString() {
			return "{" + intervalNumber + ", "
					+ (white ? "w" : "b") + "}";
		}
	}
}
