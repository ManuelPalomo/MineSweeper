package graphics.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import board.Board;
import utils.Cons;

public class ButtonListener implements ActionListener {
	private Board board;
	private JPanel buttonPanel;

	public ButtonListener(Board board, JPanel buttonPanel) {
		this.board = board;
		this.buttonPanel = buttonPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		// Trough getClientProperty we can get the coords of the button that was
		// pressed
		int x = (int) buttonPressed.getClientProperty("X");
		int y = (int) buttonPressed.getClientProperty("Y");
		int cellValue = board.getCellContent(x, y);

		switch (cellValue) {
		case Cons.EMPTY:
			break;
		case Cons.MINE:
			break;
		default:
			break;
		}

	}

}
