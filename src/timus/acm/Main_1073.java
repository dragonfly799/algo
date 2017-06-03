package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_1073 {
    private static Map<Integer, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        System.out.println(calc(n));
    }

    private static int calc(int n) {
        Integer cached = cache.get(n);
        if (cached != null) {
            return cached;
        }

        int sqrt = (int) Math.sqrt(n);
        if (sqrt * sqrt == n) {
            cache.put(n, 1);
            return 1;
        }

        sqrt = (int) Math.sqrt(n / 2);
        int res = n;

        for (int i = sqrt; i >= 1; i--) {
            int calc = calc(n - i * i);
            if (calc < res) {
                res = calc + 1;
            }
            if (res == 2) {
                break;
            }
        }
        cache.put(n, res);
        return res;
    }
}
