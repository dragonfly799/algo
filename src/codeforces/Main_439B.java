package codeforces;

import java.io.*;
import java.util.Random;

public class Main_439B {

    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        System.setIn(Main_439B.class.getResourceAsStream("/input"));
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        int n = readInt(tokenizer);
        int x = readInt(tokenizer);
        int[] array = readArray(n, tokenizer);

        quickSort(array, 0, n - 1);

        long hours = 0;
        for (int i = 0; i < n; i++) {
            hours += ((long) x) * array[i];
            if (x > 1) {
                x--;
            }
        }

        System.out.println(hours);
    }

    public static void quickSort(int[] array, int start, int end) {

        int left = start;
        int right = end;
        int pivot = array[calculatePivot(start, end)];

        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;
                right--;
            }
        }

        if (start < right) {
            quickSort(array, start, right);
        }
        if (end > left) {
            quickSort(array, left, end);
        }
    }

    private static int calculatePivot(int start, int end) {
//        return (start + end) / 2;
        return random.nextInt(end - start + 1) + start;
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
