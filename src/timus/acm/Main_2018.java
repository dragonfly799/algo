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
        for (int i = 1; i < n; i++) {
            res = (res * 2 - canNotAdd1(i) - canNotAdd2(i)) % MOD;
            if (res < 0) {
                res += MOD;
            }
        }
        return res;
    }

    private static long canNotAdd1(int n) {
        if (n < a) {
            return 0;
        }
        if (n == a) {
            return 1;
        }
        return (cache2[n - a] - cache2[n - a - 1]);
    }

    private static long canNotAdd2(int n) {
        if (n < b) {
            return 0;
        }
        if (n == b) {
            return 1;
        }
        return (cache1[n - b] - cache1[n - b - 1]);
    }

    private static void calcCaches() {
        cache1[0] = 1;
        cache2[0] = 1;
        for (int i = 1; i < n; i++) {
            int sum = cache2[i - 1];
            if (i > a) {
                sum = (sum - cache2[i - a - 1]) % MOD;
            }
            cache1[i] = (cache1[i - 1] + sum) % MOD;

            sum = cache1[i - 1];
            if (i > b) {
                sum = (sum - cache1[i - b - 1]) % MOD;
            }
            cache2[i] = (cache2[i - 1] + sum) % MOD;
        }
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
