package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1084 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] split = in.readLine().split(" ");
		double side = Double.parseDouble(split[0]);
		double cord = Double.parseDouble(split[1]);

		double halfSide = side / 2;
		if (cord <= halfSide) {
			System.out.printf("%.3f", cord * cord * Math.PI);
		} else if (cord >= halfSide * Math.sqrt(2)) {
			System.out.printf("%.3f", side * side);
		} else {
			double angle = 2 * Math.acos(halfSide / cord);
			double S = cord * cord * (Math.PI - 2 * (angle - Math.sin(angle)));
			System.out.printf("%.3f", S);
		}

	}
}
