package timus.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1033 {

	private static int size;
	private static Cell[][] labyrinth;

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

		int wallsCount = go(0, 0);
		if (labyrinth[size - 1][size - 1].canGo) {
			wallsCount += go(size - 1, size - 1);
		}
		System.out.println(wallsCount * 9);
	}

	private static int go(int row, int column) {
		int walls = checkWalls(row, column);
		labyrinth[row][column].canGo = false;

		if (canGo(row - 1, column)) walls += go(row - 1, column);
		if (canGo(row, column - 1)) walls += go(row, column - 1);
		if (canGo(row + 1, column)) walls += go(row + 1, column);
		if (canGo(row, column + 1)) walls += go(row, column + 1);
		return walls;
	}

	private static boolean canGo(int row, int column) {
		return row >= 0 && row < size
				&& column >= 0 && column < size
				&& labyrinth[row][column].canGo;
	}

	private static int checkWalls(int row, int column) {
		int walls = 0;
		if (isStone(row, column, Side.LEFT)) walls++;
		if (isStone(row, column, Side.RIGHT)) walls++;
		if (isStone(row, column, Side.TOP)) walls++;
		if (isStone(row, column, Side.BOTTOM)) walls++;

		return walls;
	}

	private static boolean isStone(int row, int column, Side side) {
		switch (side) {
			case TOP:
				return row == 0 && column != row || row > 0 && labyrinth[row - 1][column].stone;
			case BOTTOM:
				return row == size - 1 && column != row || row < size - 1 && labyrinth[row + 1][column].stone;
			case LEFT:
				return column == 0 && column != row || column > 0 && labyrinth[row][column - 1].stone;
			case RIGHT:
				return column == size - 1 && column != row || column < size - 1 && labyrinth[row][column + 1].stone;
		}
		return false;
	}

	private enum Side {LEFT, RIGHT, TOP, BOTTOM}

	private static class Cell {
		boolean stone;
		boolean canGo;

		public Cell(boolean stone) {
			this.stone = stone;
			this.canGo = !stone;
		}
	}

}
