package main;

import board.Board;
import graphics.MineGUI;

public class MineSweeper {
	public static void main(String[] args){
		Board board = new Board(8,8,9);
		MineGUI gui = new MineGUI(800, 800, board);
		System.out.println(board.toString());
		
			
		}
	}

