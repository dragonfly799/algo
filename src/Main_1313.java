import utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main_1313 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.valueOf(reader.readLine());

        int[][] matrix = StreamUtils.readSquareMatrix(size, new StreamTokenizer(reader));

        for (int i = 0; i < size; i++) {
            for (int j = i; j >=0 ; j--) {
                System.out.print(matrix[j][i-j] + " ");
            }
        }
        for (int i = 1; i < size; i++) {
            for (int j = size-1; j >=i; j--) {
                System.out.print(matrix[j][size-j+i-1] + " ");
            }
        }
    }

}