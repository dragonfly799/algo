package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1020 {

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n = readInt(in);
		double r = readDouble(in);

		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			points[i] = new Point(readDouble(in), readDouble(in));
		}

		double length = 0;
		if (n == 1) {
			length = Math.PI * 2 * r;
		} else for (int i = 0; i < n; i++) {
			Point o = points[i];
			Point prev = prev(points, i);
			Point next = next(points, i);

			Point vectorToPrev = new Point(prev.x - o.x, prev.y - o.y);
			Point vectorToNext = new Point(next.x - o.x, next.y - o.y);

			double distanceToPrev = Math.sqrt(vectorToPrev.x * vectorToPrev.x + vectorToPrev.y * vectorToPrev.y);
			double distanceToNext = Math.sqrt(vectorToNext.x * vectorToNext.x + vectorToNext.y * vectorToNext.y);
			double scalar = vectorToPrev.x * vectorToNext.x + vectorToPrev.y * vectorToNext.y;

			double cos = scalar / (distanceToPrev * distanceToNext);
			double alpha = Math.acos(cos);

			length += distanceToNext;
			length += r * (Math.PI - alpha);
		}
		System.out.printf("%.2f", length);
	}

	private static Point prev(Point[] points, int i) {
		if (i == 0) {
			return points[points.length - 1];
		}
		return points[i - 1];
	}

	private static Point next(Point[] points, int i) {
		if (i == points.length - 1) {
			return points[0];
		}
		return points[i + 1];
	}

	private static int readInt(StreamTokenizer in) throws IOException {
		return (int) readDouble(in);
	}

	private static double readDouble(StreamTokenizer in) throws IOException {
		in.nextToken();
		return in.nval;
	}

	private static class Point {
		double x;
		double y;

		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

}
