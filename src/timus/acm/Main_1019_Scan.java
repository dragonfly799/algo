package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class Main_1019_Scan {

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = readInt(tokenizer);

        Point[] points = new Point[n * 2 + 2];
        Interval interval = new Interval(0, true);
        points[0] = new Point(0, true, interval);
        points[n * 2 + 1] = new Point(1000000000, false, interval);

        for (int i = 1; i <= n; i++) {
            int a = readInt(tokenizer);
            int b = readInt(tokenizer);
            boolean white = readString(tokenizer).equals("w");

            interval = new Interval(i, white);
            points[i * 2 - 1] = new Point(a, true, interval);
            points[i * 2] = new Point(b, false, interval);
        }
        Arrays.sort(points);

        SortedSet<Interval> currentIntervals = new TreeSet<>();
        int start = 0;
        int end = 0;
        int maxLength = 0;

        int currStart = 0;
        int currEnd = 0;
        int p = 0;
        boolean currentIsWhite = true;

        int i = 0;
        while (i < points.length) {
            while (i < points.length && points[i].point == p) {
                if (points[i].start) {
                    currentIntervals.add(points[i].interval);
                } else {
                    currentIntervals.remove(points[i].interval);
                }
                i++;
            }

            boolean nextIsWhite = !currentIntervals.isEmpty() && currentIntervals.last().white;
            if (currentIsWhite != nextIsWhite) {
                if (currentIsWhite) {
                    currEnd = p;
                    if (currEnd - currStart > maxLength) {
                        maxLength = currEnd - currStart;
                        start = currStart;
                        end = currEnd;
                    }
                } else {
                    currStart = p;
                }
                currentIsWhite = nextIsWhite;
            }

            if (i < points.length) {
                p = points[i].point;
            }
        }

        System.out.println(start + " " + end);
    }

    private static int readInt(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static String readString(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }

    private static class Point implements Comparable<Point> {
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

        @Override
        public int compareTo(Point o) {
            return point - o.point;
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
