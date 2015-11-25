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

public class MineGUI {
	private int height;
	private int length;
	private Board board;
	private ButtonGrid buttonGrid;
	private JFrame frame;
	private JPanel buttonsPanel;
	private ActionListener menuListener;
	public static boolean gameOver;

	public MineGUI(int height, int length, Board board) {
		this.height = height;
		this.length = length;
		this.board = board;
		gameOver = false;
		this.buttonGrid = new ButtonGrid(board.getSizeX(), board.getSizeY(), board);
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

	public static void gameOver() {
		gameOver = true;

	}

	private void launchOptionsDialog() {
		
		//The panel will go inside the dialog
		JPanel optionsPanel = new JPanel();
		
		JRadioButton easyButton = new JRadioButton("Easy, 10 Mines, 9x9 grid");
		JRadioButton mediumButton = new JRadioButton("Medium,40 Mines, 16x16 grid");
		JRadioButton hardButton = new JRadioButton("Hard,99 Mines,16x30 grid");
		
		ButtonGroup group = new ButtonGroup();
		group.add(easyButton);
		group.add(mediumButton);
		group.add(hardButton);
		
		//AddListeners
		
		optionsPanel.add(easyButton);
		optionsPanel.add(mediumButton);
		optionsPanel.add(hardButton);
		

		JDialog optionsDialog = new JDialog(frame,"Opciones");
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
}
