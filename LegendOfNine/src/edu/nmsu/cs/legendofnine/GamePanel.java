package edu.nmsu.cs.legendofnine;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/*
 * Primary settings for the game panel window. Includes tile size and size of the game window.
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	
	// Screen settings
	final int originalTileSize = 16; // 16 by 16 tile. FIXME when graphics are updated.
	final int scale = 4;                            // Scales up pixel art by 4x
	final int tileSize = originalTileSize * scale;  //Actual size of tile is 64x64.
	final int maxScreenCol = 16;   // Number of tiles that fit on the game screen horizontally
	final int maxScreenRow = 12;   // Number of tiles that fit on the game screen vertically
	
	final int screenWidth = tileSize * maxScreenCol;  // 1024 pixels
	final int screenHeight = tileSize * maxScreenRow; // 768 pixels
	
	
	
	Thread gameThread;
	
	
	
	/**
	 * Constructor for GamePanel
	 */
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Drawing done in offscreen painting buffer
	} // end constructor
	
	
	
	/**
	 * Run method for game
	 */
	@Override
	public void run() {
		//
	} // end run
	
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	} // end startGameThread

} // end GamePanel
