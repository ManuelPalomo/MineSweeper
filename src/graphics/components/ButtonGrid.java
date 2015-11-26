package graphics.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import board.Board;
import graphics.MineGUI;
import utils.Cons;

public class ButtonGrid {
	private int sizeX;
	private int sizeY;
	private Board board;
	private ActionListener listener;
	private JPanel buttonPanel;
	private JButton[][] buttonMatrix;

	public ButtonGrid(int sizeX, int sizeY, Board board) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.board = board;
		this.buttonMatrix = new JButton[sizeX][sizeY];
		listener = initializeActionListener();
		this.buttonPanel = fillButtonMatrix(sizeX, sizeY);
	}

	private JPanel fillButtonMatrix(int sizeX, int sizeY) {
		JPanel panel = new JPanel(new GridLayout(sizeX, sizeY));
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				JButton button = new JButton();
				button.putClientProperty("x", x);
				button.putClientProperty("y", y);
				button.setPreferredSize(new Dimension(30, 30));
				button.setMargin(new Insets(0, 0, 0, 0));
				button.addActionListener(listener);
				buttonMatrix[x][y] = button;

				panel.add(button);

			}
		}
		return panel;
	}

	/**
	 * Paints a button to reflect that it was clicked If a bomb is clicked, game
	 * needs to terminate finish
	 * 
	 * @param button
	 * @param cellContent
	 */
	public void paintButton(JButton button, int cellContent) {
		button.setBackground(Color.white);
		button.setBorderPainted(false);
		button.setEnabled(false);
		if (cellContent == Cons.MINE) {
			button.setText("*");
			button.setBackground(Color.red);
			MineGUI.gameOver();
		} else if (cellContent == Cons.EMPTY) {
			LinkedList<JButton> checked = new LinkedList<JButton>();
			revealAdjacentBlanks(button, checked);
		} else {
			button.setText(Integer.toString(cellContent));
		}

	}

	public void revealSolution() {
		for (JButton[] buttonRow : buttonMatrix) {
			for (JButton button : buttonRow) {
				paintButton(button, board.getCellContent((Integer) button.getClientProperty("x"),
						(Integer) button.getClientProperty("y")));
			}
		}

	}

	/**
	 * According to the rules, if a blank spot is found, the adjacent squares
	 * need to be recursively revealed
	 */
	public void revealAdjacentBlanks(JButton button, LinkedList<JButton> checkedList) {
		checkedList.add(button);
		int buttonX = (Integer) button.getClientProperty("x");
		int buttonY = (Integer) button.getClientProperty("y");

		button.setBackground(Color.white);
		button.setBorderPainted(false);
		button.setEnabled(false);
		button.setText("");

		for (int x = buttonX - 1; x < buttonX + 2; x++) {
			for (int y = buttonY - 1; y < buttonY + 2; y++) {
				if (x >= 0 && x < sizeX && y >= 0 && y < sizeY && board.getCellContent(x, y) == Cons.EMPTY
						&& (buttonX != x || buttonY != y) && !checkedList.contains(buttonMatrix[x][y])) {
					checkedList.add(buttonMatrix[x][y]);
					revealAdjacentBlanks(buttonMatrix[x][y], checkedList);
				}
			}
		}

	}

	/**
	 * Iterates all over the button list and repaints all the components back to
	 * their original look.
	 */
	public void resetBoard() {
		for (JButton[] buttonRow : buttonMatrix) {
			for (JButton button : buttonRow) {
				button.setText("");
				button.setBackground(null); // Aparently, null is the default
											// color
											// of the JButton
				button.setBorderPainted(true);
				button.setEnabled(true);
			}
		}

	}

	private ActionListener initializeActionListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!MineGUI.gameOver) {
					JButton buttonPressed = (JButton) e.getSource();
					int x = (int) buttonPressed.getClientProperty("x");
					int y = (int) buttonPressed.getClientProperty("y");
					int cellContent = board.getCellContent(x, y);
					paintButton(buttonPressed, cellContent);
				}
			}
		};

		return listener;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
