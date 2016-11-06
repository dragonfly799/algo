package codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_545D {

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = readInt(tokenizer);
        int[] array = readArray(n, tokenizer);

        Arrays.sort(array);

        int sum = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] >= sum ) {
                sum+= array[i];
                res++;
            }
        }
        System.out.println(res);
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
