package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;
import graphics.components.ButtonGrid;

public class MineGUI {
	private int height;
	private int length;
	private Board board;
	private ButtonGrid buttonGrid;
	private JFrame frame;
	private JPanel buttonsPanel;

	public MineGUI(int height, int length, Board board) {
		this.height = height;
		this.length = length;
		this.board = board;
		this.buttonGrid = new ButtonGrid(board.getSizeX(), board.getSizeY());
		this.buttonsPanel = buttonGrid.getButtonPanel();               
		this.frame = new JFrame("MineSweeper");
		frame.add(buttonsPanel);

		frame.setSize(height, length);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}



}
