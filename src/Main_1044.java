import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1044 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] sums = new int[9 * n / 2 + 1];
        for (int i = 0; i < Math.pow(10, (n / 2)); i++) {
            sums[sum(i)]++;
        }
        int result = 0;
        for (int i = 0; i < sums.length; i++) {
            result += sums[i] * sums[i];
        }
        System.out.println(result);
    }

    private static int sum(int i) {
        int res = 0;
        while (i > 0) {
            res += i % 10;
            i /= 10;
        }
        return res;
    }
}
