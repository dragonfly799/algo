package timus.acm;

import java.io.*;

public class Main_1100 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		Team[] teams = new Team[n];
		Team[] tmp = new Team[n];

		for (int i = 0; i < n; i++) {
			String line = reader.readLine();
			int space = line.indexOf(' ');
			int id = Integer.parseInt(line.substring(0, space));
			int solved = Integer.parseInt(line.substring(space + 1));
			teams[i] = new Team(id, solved);
		}

		sort(teams, tmp, 0, n - 1);

		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < n; i++) {
			out.print(teams[i].id);
			out.print(" ");
			out.println(teams[i].solved);
		}
		out.flush();
	}

	private static void sort(Team[] teams, Team[] tmp, int start, int end) {
		if (start == end) {
			return;
		}

		int middle = (start + end) / 2;
		sort(teams, tmp, start, middle);
		sort(teams, tmp, middle + 1, end);

		int left = start;
		int right = middle + 1;
		int tmpIndex = start;
		while (left <= middle && right <= end) {
			if (teams[left].solved >= teams[right].solved) {
				tmp[tmpIndex] = teams[left];
				left++;
			} else {
				tmp[tmpIndex] = teams[right];
				right++;
			}
			tmpIndex++;
		}
		while (left <= middle) {
			tmp[tmpIndex] = teams[left];
			left++;
			tmpIndex++;
		}
		while (right <= end) {
			tmp[tmpIndex] = teams[right];
			right++;
			tmpIndex++;
		}

		System.arraycopy(tmp, start, teams, start, end - start + 1);
	}

	private static class Team {
		int id;
		int solved;

		public Team(int id, int solved) {
			this.id = id;
			this.solved = solved;
		}
	}
}
