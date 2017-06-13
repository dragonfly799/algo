package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1146 {

    private static int n;
    private static int[][] matrix;
    private static int maxSum;
    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        n = readInt(in);

        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = readInt(in);
            }
        }

        preCalc();
        calc();
        System.out.println(maxSum);
    }

    private static void preCalc() {
        cache = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = preSum(i, j);
                cache[i][j] = sum;
            }
        }
    }

    private static int preSum(int x, int y) {
        int sum = matrix[x][y];
        if (x > 0) {
            sum += cache[x - 1][y];
        }
        if (y > 0) {
            sum += cache[x][y - 1];
        }
        if (x > 0 && y > 0) {
            sum -= cache[x - 1][y - 1];
        }
        return sum;
    }

    private static void calc() {
        maxSum = Integer.MIN_VALUE;
        for (int startX = 0; startX < n; startX++) {
            for (int startY = 0; startY < n; startY++) {
                for (int endX = startX; endX < n; endX++) {
                    for (int endY = startY; endY < n; endY++) {
                        int sum = sum(startX, startY, endX, endY);
                        if (sum > maxSum) {
                            maxSum = sum;
                        }
                    }
                }
            }
        }
    }

    private static int sum(int startX, int startY, int endX, int endY) {
        int sum = cache[endX][endY];
        if (startX > 0) {
            sum -= cache[startX - 1][endY];
        }
        if (startY > 0) {
            sum -= cache[endX][startY - 1];
        }
        if (startX > 0 && startY > 0) {
            sum += cache[startX - 1][startY - 1];
        }
        return sum;
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
