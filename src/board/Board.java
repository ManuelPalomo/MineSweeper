package board;

import utils.Cons;

/**
 * Logic part of the game, manages the board and it's contents
 * 
 * Numbers: 0=Empty 1=Mine
 * 
 * @author Manuel Palomo <manuel_palomo@hotmail.es>
 *
 */
public class Board {
	private int sizeX;
	private int sizeY;
	private int mines;
	private int[][] board;

	public Board(int sizeX, int sizeY, int mines) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.mines = mines;
		this.board = setupBoard();

	}

	private int[][] setupBoard() {
		int[][] board = initializeBoardToEmpty();
		for (int i = 0; i < mines; i++) {
			placeMine(board);
		}
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				board[x][y] = calculateCellValue(x, y);
			}
		}
		return board;
	}

	private int[][] initializeBoardToEmpty() {
		board = new int[sizeX][sizeY];
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				board[x][y] = Cons.EMPTY;
			}
		}
		return board;

	}

	/**
	 * Generates a random address and places a mine in it, if there's a mine
	 * already generates another one
	 * 
	 * @return
	 */
	private void placeMine(int board[][]) {
		boolean placed = false;
		int x = (int) (Math.random() * sizeX);
		int y = (int) (Math.random() * sizeY);

		while (!placed) {
			if (isCellEmpty(x, y)) {
				board[x][y] = Cons.MINE;
				placed = true;
			} else {
				x = (int) (Math.random() * sizeX);
				y = (int) (Math.random() * sizeY);
			}
		}
	}

	/**
	 * Calculate and initializes the given cell
	 */
	private int calculateCellValue(int x, int y) {
		int bombs = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (x + i >= 0 && y + j >= 0 && x + i < sizeX && y + j < sizeY && x != 0 && y != 0) {
					if (board[x + i][y + j] == Cons.MINE) {
						bombs++;
					}
				}
			}
		}
		return bombs;

	}

	private boolean isCellEmpty(int x, int y) {
		if (board[x][y] == Cons.EMPTY) {
			return true;
		} else {
			return false;
		}
	}

	public int getCellContent(int x, int y) {
		return board[x][y];
	}

	public String toString() {
		String boardToString = "";
		for (int x = 0; x < sizeX; x++) {
			String row = "[";
			for (int y = 0; y < sizeY; y++) {
				row += Integer.toString(board[x][y]);
			}
			row += "]";
			boardToString += row;
		}
		return boardToString;

	}
}
