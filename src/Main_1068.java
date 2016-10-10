import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Сумма
 * http://acm.timus.ru/problem.aspx?space=1&num=1068
 */
public class Main_1068 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		if (n > 1) {
			System.out.println((n + 1) * n / 2);
		} else {
			System.out.println((n + 1) * (2 - n) / 2);
		}
	}

}
