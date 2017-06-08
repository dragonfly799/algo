package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1119 {

    private static int n;
    private static int m;
    private static int[][] diagonals;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = readInt(in);
        m = readInt(in);
        int k = readInt(in);

        diagonals = new int[n + 1][m + 1];

        for (; k > 0; k--) {
            diagonals[readInt(in)][readInt(in)] = 1;
        }

        calc();
        long path = Math.round((m + n + (Math.sqrt(2) - 2) * diagonals[n][m]) * 100);
        System.out.println(path);
    }

    private static void calc() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int res = diagonals[i][j];
                if (res == 1) {
                    res += diagonals[i - 1][j - 1];
                }
                res = Math.max(res, diagonals[i - 1][j]);
                res = Math.max(res, diagonals[i][j - 1]);

                diagonals[i][j] = res;
            }
        }
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
