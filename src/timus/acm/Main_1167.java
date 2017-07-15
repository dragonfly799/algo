package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1167 {

    private static int[][] cache;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = readInt(in);
        int k = readInt(in);

        int blackHorses = 0;
        int[] horses = new int[n];
        for (int i = 0; i < n; i++) {
            int horse = readInt(in);
            blackHorses += horse;
            horses[i] = blackHorses;
        }

        cache = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                cache[i][j] = -1;
            }
        }

        System.out.println(calcMin(horses, n, k));
    }

    private static int calcMin(int[] horses, int n, int k) {
        int cached = cache[n][k];
        if (cached != -1) {
            return cached;
        }

        int min;
        if (k == 1) {
            min = calcUnhappy(horses, horses.length - n, horses.length);
        } else {
            min = Integer.MAX_VALUE;
            for (int i = 1; i <= n - k + 1; i++) {
                int curr = calcUnhappy(horses, horses.length - n, horses.length - n + i) + calcMin(horses, n - i, k - 1);
                if (curr < min) {
                    min = curr;
                }
            }
        }
        cache[n][k] = min;
        return min;
    }

    private static int calcUnhappy(int[] horses, int startIndex, int endIndex) {
        if (endIndex - startIndex == 1) {
            return 0;
        }

        int black = horses[endIndex - 1];
        if (startIndex > 0) {
            black -= horses[startIndex - 1];
        }
        int white = (endIndex - startIndex) - black;
        return black * white;
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
