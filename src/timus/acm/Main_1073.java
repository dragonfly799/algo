package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1073 {

    private static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        cache = new int[n + 1];

        System.out.println(calcIter(n));
    }

    private static int calcIter(int n) {
        for (int i = 1; i <= n; i++) {
            int res = i;
            int sqrt = (int) Math.sqrt(i);
            if (sqrt * sqrt == i) {
                res = 1;
            } else {
                for (int j = 1; j <= sqrt; j++) {
                    res = Math.min(res, cache[i - j * j] + 1);
                    if (res == 2) {
                        break;
                    }
                }
            }
            cache[i] = res;
        }
        return cache[n];
    }

    private static int calcRec(int n) {
        int cached = cache[n];
        if (cached > 0) {
            return cached;
        }

        int sqrt = (int) Math.sqrt(n);
        if (sqrt * sqrt == n) {
            cache[n] = 1;
            return 1;
        }

        sqrt = (int) Math.sqrt(n / 2);
        int res = n;

        for (int i = sqrt; i >= 1; i--) {
            int calc = calcRec(n - i * i);
            if (calc < res) {
                res = calc + 1;
            }
            if (res == 2) {
                break;
            }
        }
        cache[n] = res;
        return res;
    }
}
