package edu.nmsu.cs.legendofnine;

import javax.swing.JFrame;

public class Main {

	/**
	 * Main function that the game runs in.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // Cannot resize game window
		window.setTitle("Legend of Nine");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); // Fits to layout of GamePanel params set in GamePanel.java
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}

}
