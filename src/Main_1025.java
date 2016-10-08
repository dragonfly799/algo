import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main_1025 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.valueOf(reader.readLine());
        int[] groups = readArray(size, new StreamTokenizer(reader));
        Arrays.sort(groups);

        int result = 0;
        for (int i = 0; i < (size / 2) + 1; i++) {
            result += groups[i] / 2 + 1;
        }

        System.out.print(result);
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
