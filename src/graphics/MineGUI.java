package graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;

public class MineGUI {
	private int height;
	private int length;
	private Board board;
	private JFrame frame;
	private JPanel panel;
	private JButton[][] buttons;

	public MineGUI(int height, int length,Board board) {
		this.height = height;
		this.length = length;
		this.board=board;
		this.frame = new JFrame("MineSweeper");

		frame.setSize(height, length);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
