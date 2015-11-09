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
			placeMine();
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
	private void placeMine() {
		boolean placed = false;
		int x = (int) (Math.random() * sizeX);
		int y = (int) (Math.random() * sizeY);

		while (!placed) {
			if (isCellEmpty(x, y)) {
				board[x][y] = Cons.MINE;
			} else {
				x = (int) (Math.random() * sizeX);
				y = (int) (Math.random() * sizeY);
			}
		}
	}

	private boolean isCellEmpty(int x, int y) {
		if (board[x][y] == Cons.EMPTY) {
			return true;
		} else {
			return false;
		}
	}

	private int getCellContent(int x, int y) {
		return board[x][y];
	}
}
