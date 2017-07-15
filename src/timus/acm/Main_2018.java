package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_2018 {

    private static final int MOD = 1000000007;

    private static int a;
    private static int b;
    private static int n;

    private static int[] cache1;
    private static int[] cache2;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = readInt(in);
        a = readInt(in);
        b = readInt(in);

        cache1 = new int[n];
        cache2 = new int[n];
        calcCaches();

        System.out.println(calc());
    }

    private static long calc() {
        long res = 2;
        for (int i = 2; i <= n; i++) {
            res *= 2;
            if (i > a) {
                res -= cache2[i - 1 - a];
            }
            if (i > b) {
                res -= cache1[i - 1 - b];
            }
            res %= MOD;
            if (res < 0) {
                res += MOD;
            }
        }
        return res;
    }

    private static void calcCaches() {
        cache1[0] = 1;
        cache2[0] = 1;
        for (int i = 1; i < n; i++) {
            int sum = 0;
            for (int j = 1; j <= a && j <= i; j++) {
                sum = (sum + cache2[i - j]) % MOD;
            }
            cache1[i] = sum;

            sum = 0;
            for (int j = 1; j <= b && j <= i; j++) {
                sum = (sum + cache1[i - j]) % MOD;
            }
            cache2[i] = sum;
        }
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
