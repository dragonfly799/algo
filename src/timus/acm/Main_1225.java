package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1225 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        System.out.println(calc(n));
    }

    private static long calc(int n) {
        if (n == 1 || n == 2) {
            return 2;
        }

        long prev1 = 2;
        long prev2 = 2;

        for (int i = 2; i < n; i++) {
            long tmp = prev2;
            prev2 += prev1;
            prev1 = tmp;
        }

        return prev2;
    }
}
