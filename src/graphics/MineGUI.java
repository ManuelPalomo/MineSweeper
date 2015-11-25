package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import board.Board;
import graphics.components.ButtonGrid;
import utils.Cons;

public class MineGUI {
	private int height;
	private int length;
	private int sizeX;
	private int sizeY;
	private int mines;
	private int difficulty;
	private Board board;
	private ButtonGrid buttonGrid;
	private JFrame frame;
	private JPanel buttonsPanel;
	private ActionListener menuListener;
	public static boolean gameOver;

	public MineGUI(int height, int length, int difficulty) {
		this.height = height;
		this.length = length;
		this.difficulty = difficulty;
		switch (difficulty) {
		case Cons.EASY:
			this.sizeX = Cons.EASY_X;
			this.sizeY = Cons.EASY_Y;
			this.mines = Cons.EASY_MINES;
			break;

		case Cons.MEDIUM:
			this.sizeX = Cons.MEDIUM_X;
			this.sizeY = Cons.MEDIUM_Y;
			this.mines = Cons.MEDIUM_MINES;
			break;

		case Cons.HARD:
			this.sizeX = Cons.HARD_X;
			this.sizeY = Cons.HARD_Y;
			this.mines = Cons.HARD_MINES;
			break;
		}
		this.board = new Board(sizeX, sizeY, mines);
		gameOver = false;
		this.buttonGrid = new ButtonGrid(sizeX, sizeY, board);
		this.menuListener = initializeMenuListener();
		buttonsPanel = buttonGrid.getButtonPanel();

		this.frame = new JFrame("MineSweeper");
		frame.add(buttonsPanel);
		frame.setJMenuBar(createMenuBar(menuListener));

		frame.setSize(height, length);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	private JMenuBar createMenuBar(ActionListener listener) {
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenu helpMenu = new JMenu("Help");

		// Game menu items
		JMenuItem newGameMenu = new JMenuItem("New Game");
		newGameMenu.setActionCommand("new");
		newGameMenu.addActionListener(listener);

		JMenuItem optionsGameMenu = new JMenuItem("Options");
		optionsGameMenu.addActionListener(listener);
		optionsGameMenu.setActionCommand("options");

		JMenuItem exitGameMenu = new JMenuItem("Exit");
		exitGameMenu.addActionListener(listener);
		exitGameMenu.setActionCommand("exit");

		gameMenu.add(newGameMenu);
		gameMenu.add(optionsGameMenu);
		gameMenu.add(exitGameMenu);

		menuBar.add(gameMenu);

		// Help menu items

		JMenuItem viewHelpMenu = new JMenuItem("View Help");
		viewHelpMenu.addActionListener(listener);
		viewHelpMenu.setActionCommand("help");

		JMenuItem aboutHelpMenu = new JMenuItem("About");
		aboutHelpMenu.addActionListener(listener);
		aboutHelpMenu.setActionCommand("about");

		helpMenu.add(viewHelpMenu);
		helpMenu.add(aboutHelpMenu);

		menuBar.add(helpMenu);

		return menuBar;

	}

	/**
	 * Reinitializes everything, assigns a new board and buttonGrid, then
	 * creates another instance of the GUI and destroy the last one
	 */
	public void forceReset() {
		board = new Board(sizeX, sizeY, mines);
		buttonGrid = new ButtonGrid(sizeX, sizeY, board);
		new MineGUI(height, length, difficulty);
		frame.dispose();

	}

	public static void gameOver() {
		gameOver = true;

	}

	private void launchOptionsDialog() {

		// The panel will go inside the dialog
		JPanel optionsPanel = new JPanel();

		ActionListener listener = initializeOptionsListener();

		JRadioButton easyButton = new JRadioButton("Easy, 10 Mines, 9x9 grid");
		easyButton.setActionCommand("easy");
		easyButton.addActionListener(listener);

		JRadioButton mediumButton = new JRadioButton("Medium,40 Mines, 16x16 grid");
		mediumButton.setActionCommand("medium");
		mediumButton.addActionListener(listener);

		JRadioButton hardButton = new JRadioButton("Hard,99 Mines,16x30 grid");
		hardButton.setActionCommand("hard");
		hardButton.addActionListener(listener);

		// Mark the button as selected according with the current difficulty
		switch (difficulty) {
		case Cons.EASY:
			easyButton.setSelected(true);
			break;
		case Cons.MEDIUM:
			mediumButton.setSelected(true);
			break;
		case Cons.HARD:
			hardButton.setSelected(true);
			break;
		}
		// Group Buttons
		ButtonGroup group = new ButtonGroup();
		group.add(easyButton);
		group.add(mediumButton);
		group.add(hardButton);

		optionsPanel.add(easyButton);
		optionsPanel.add(mediumButton);
		optionsPanel.add(hardButton);

		JDialog optionsDialog = new JDialog(frame, "Opciones");
		optionsDialog.add(optionsPanel);

		optionsDialog.pack();
		optionsDialog.setVisible(true);
	}

	private ActionListener initializeMenuListener() {
		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();

				switch (command) {

				// Reset the current board, asigning a new board and clearing
				// the buttons
				case "new":
					buttonGrid.resetBoard();
					board = new Board(board.getSizeX(), board.getSizeY(), board.getMinesNumber());
					buttonGrid.setBoard(board);
					gameOver = false;
					break;

				case "options":
					launchOptionsDialog();
					break;

				case "exit":
					System.exit(90);
					break;

				case "help":
					break;

				case "about":
					break;

				}

			}
		};
		return menuListener;
	}

	private ActionListener initializeOptionsListener() {
		ActionListener optionsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				switch (command) {
				case "easy":
					difficulty = Cons.EASY;
					break;

				case "medium":
					difficulty = Cons.MEDIUM;
					break;

				case "hard":
					difficulty = Cons.HARD;
					break;

				}
				forceReset();

			}
		};
		return optionsListener;
	}
}
