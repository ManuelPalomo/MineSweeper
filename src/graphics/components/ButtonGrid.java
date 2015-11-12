package graphics.components;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import board.Board;
import utils.Cons;

public class ButtonGrid {
	private JPanel buttonPanel;
	private int sizeX;
	private int sizeY;
	private Board board;
	private ActionListener listener;

	public ButtonGrid(int sizeX, int sizeY, Board board) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.board = board;
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
				panel.add(button);

			}
		}
		return panel;
	}

	/**
	 * Paints a button to reflect that it was clicked
	 * 
	 * @param button
	 * @param cellContent
	 */
	public void paintButton(JButton button, int cellContent) {
		if (cellContent == Cons.MINE) {
			button.setText("Mine");
		} else {
			button.setText(Integer.toString(cellContent));
		}

	}

	private ActionListener initializeActionListener() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton buttonPressed = (JButton) e.getSource();
				int x = (int) buttonPressed.getClientProperty("x");
				int y = (int) buttonPressed.getClientProperty("y");
				int cellContent = board.getCellContent(x, y);
				paintButton(buttonPressed, cellContent);

			}
		};

		return listener;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

}
