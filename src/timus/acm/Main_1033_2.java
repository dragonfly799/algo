package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Main_1033_2 {

	private static int size;
	private static Cell[][] labyrinth;
	private static int wallsCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(reader.readLine());

		labyrinth = new Cell[size][size];
		for (int i = 0; i < size; i++) {
			String line = reader.readLine();
			for (int j = 0; j < size; j++) {
				labyrinth[i][j] = new Cell(line.charAt(j) == '#');
			}
		}

		go(1, Arrays.asList(0, 0, size - 1, size - 1));
		System.out.println(wallsCount * 9);
//		printLabyrinth();
	}

	private static void printLabyrinth() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(labyrinth[i][j].stone ? "# " : labyrinth[i][j].distance + " ");
			}
			System.out.println();
		}
	}

	private static void go(int distance, List<Integer> coords) {
		if (coords.isEmpty()) {
			return;
		}

		List<Integer> nextCoords = new ArrayList<>();
		for (int i = 0; i < coords.size(); i += 2) {
			int row = coords.get(i);
			int column = coords.get(i + 1);

			Cell cell = labyrinth[row][column];
			if (cell.stone || cell.distance > 0) {
				continue;
			}

			wallsCount += checkWalls(row, column);
			cell.distance = distance;

			checkAndAdd(nextCoords, row - 1, column);
			checkAndAdd(nextCoords, row, column - 1);
			checkAndAdd(nextCoords, row + 1, column);
			checkAndAdd(nextCoords, row, column + 1);
		}

		go(distance + 1, nextCoords);
	}

	private static void checkAndAdd(List<Integer> nextCoords, int row, int column) {
		if (row >= 0 && row < size && column >= 0 && column < size) {
			nextCoords.add(row);
			nextCoords.add(column);
		}
	}

	private static int checkWalls(int row, int column) {
		int walls = 0;
		if (column == 0 && column != row || column > 0 && labyrinth[row][column - 1].stone)
			walls++;
		if (column == size - 1 && column != row || column < size - 1 && labyrinth[row][column + 1].stone)
			walls++;
		if (row == 0 && column != row || row > 0 && labyrinth[row - 1][column].stone)
			walls++;
		if (row == size - 1 && column != row || row < size - 1 && labyrinth[row + 1][column].stone)
			walls++;

		return walls;
	}

	private static class Cell {
		boolean stone;
		int distance;

		public Cell(boolean stone) {
			this.stone = stone;
		}
	}
}
