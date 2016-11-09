package codeforces;

import java.io.*;
import java.util.Arrays;

public class Main_439B {

    public static void main(String[] args) throws IOException {
        long before = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            System.out.print("100000 ");
//        }

        InputStream input = Main_439B.class.getResourceAsStream("/input");
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(input)));

        int n = readInt(tokenizer);
        int x = readInt(tokenizer);
        int[] array = readArray(n, tokenizer);

        Arrays.sort(array);

        long hours = 0;
        for (int i = 0; i < n; i++) {
            hours += ((long) x) * array[i];
            if (x > 1) {
                x--;
            }
        }

        System.out.println(hours);
        System.out.println("Main_439B.main time: " + (System.currentTimeMillis() - before) + " ms");
    }


    public static int readInt(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public static int[] readArray(int size, StreamTokenizer tokenizer) throws IOException {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = readInt(tokenizer);
        }
        return result;
    }
}
