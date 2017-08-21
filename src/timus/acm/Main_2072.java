package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class Main_2072 {

    private static HashMap<Integer, Wet> flowers = new HashMap<>();
    private static ArrayList<Integer> wets;
    private static Map<Integer, Long> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = readInt(in);
        for (int i = 0; i < n; i++) {
            int w = readInt(in);
            Wet wet = flowers.computeIfAbsent(w, k -> new Wet());
            wet.count++;
            if (wet.maxPos == -1 || i > wet.maxPos) {
                wet.maxPos = i;
            }
            if (wet.minPos == -1 || i < wet.minPos) {
                wet.minPos = i;
            }
        }

        wets = new ArrayList<>(flowers.keySet());
        Collections.sort(wets);

        System.out.println(calc());
    }

    private static long calc() {
        Wet wet = null;
        Wet nextWet;
        for (int i = wets.size() - 1; i >= 0; i--) {
            nextWet = wet;
            wet = flowers.get(wets.get(i));

            if (nextWet == null) {
                cache.put(wet.minPos, 0L);
                cache.put(wet.maxPos, 0L);
            } else {
                long res = Math.abs(nextWet.minPos - nextWet.maxPos) + nextWet.count;
                cache.put(wet.minPos, res + Math.min(
                        Math.abs(wet.minPos - nextWet.maxPos) + cache.get(nextWet.minPos),
                        Math.abs(wet.minPos - nextWet.minPos) + cache.get(nextWet.maxPos)
                ));

                if (wet.count > 1) {
                    cache.put(wet.maxPos, res + Math.min(
                            Math.abs(wet.maxPos - nextWet.maxPos) + cache.get(nextWet.minPos),
                            Math.abs(wet.maxPos - nextWet.minPos) + cache.get(nextWet.maxPos)
                    ));
                }
            }

        }
        long res = Math.abs(wet.minPos - wet.maxPos) + wet.count;
        res += Math.min(
                wet.maxPos + cache.get(wet.minPos),
                wet.minPos + cache.get(wet.maxPos)
        );

        return res;
    }

    private static int readInt(StreamTokenizer in) throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static class Wet {
        int count;
        int minPos = -1;
        int maxPos = -1;
    }
}
