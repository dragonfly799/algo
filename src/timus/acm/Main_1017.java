package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1017 {

    private static long[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        cache = new long[n + 1][n + 1];

        System.out.println(calc(1, n) - 1);
    }

    private static long calc(int minHeight, int n) {
        if (n < minHeight) {
            return 0;
        }

        long cached = cache[minHeight][n];
        if (cached > 0) {
            return cached;
        }
        long res = 1;
        for (int i = minHeight; i <= (n - 1) / 2; i++) {
            res += calc(i + 1, n - i);
        }
        cache[minHeight][n] = res;
        return res;
    }

}
