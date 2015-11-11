package graphics.components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonGrid {
	private JPanel buttonPanel;
	private int sizeX;
	private int sizeY;

	public ButtonGrid(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		buttonPanel = fillButtonMatrix(sizeX, sizeY);
	}

	private JPanel fillButtonMatrix(int sizeX, int sizeY) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(sizeX, sizeY));
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				JButton button = new JButton();
				button.putClientProperty("x", x);
				button.putClientProperty("y", x);
				button.setPreferredSize(new Dimension(30, 30));
				panel.add(button);
			}
		}
		return panel;

	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

}
