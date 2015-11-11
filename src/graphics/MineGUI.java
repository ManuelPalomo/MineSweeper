package graphics;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;

public class MineGUI {
	private int height;
	private int length;
	private Board board;
	private JFrame frame;
	private JPanel buttonsPanel;

	public MineGUI(int height, int length, Board board) {
		this.height = height;
		this.length = length;
		this.board = board;
		this.buttonsPanel = fillButtonMatrix(board.getSizeX(), board.getSizeY());
		this.frame = new JFrame("MineSweeper");
		frame.add(buttonsPanel);
		

		frame.setSize(height, length);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private JPanel fillButtonMatrix(int sizeX, int sizeY) {
		JPanel panel = new JPanel();
		for(int x=0;x<sizeX;x++){
			for(int y=0;y<sizeY;y++){
				JButton button = new JButton();
				button.putClientProperty("x",x);
				button.putClientProperty("y",x);
				panel.add(button);
			}
		}
		return panel;

	}

}
