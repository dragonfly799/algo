package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class Main_1152 {

    private static int n;
    private static Map<Integer, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = readInt(in);

        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = readInt(in);
            arr[i] = a;
            sum += a;
        }

        System.out.println(calc(arr, sum, (1 << n) - 1));
    }

    private static int calc(int[] arr, int sum, int bits) {
        if (sum == 0) {
            return 0;
        }
        if (cache.containsKey(bits)) {
            return cache.get(bits);
        }

        int damage = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int newSum = sum;
            int newBits = bits;
            int j = 0;
            int index = i;
            while ((bits & (1 << index)) > 0 && j < 3) {
                newBits -= (1 << index);
                newSum -= arr[index];
                j++;
                index = (index + 1) % n;
            }

            int prevIndex = (i - 1 + n) % n;
            if (j < 3 && (bits & (1 << prevIndex)) > 0) {
                continue;
            }

            if (j > 0) {
                int calc = calc(arr, newSum, newBits) + newSum;
                if (calc < damage) {
                    damage = calc;
                }
            }
        }

        cache.put(bits, damage);
        return damage;
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
