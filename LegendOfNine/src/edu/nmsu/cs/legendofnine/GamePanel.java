package edu.nmsu.cs.legendofnine;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * Primary settings for the game panel window. Includes tile size and size of the game window.
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	
	// Screen settings
	final int originalTileSize = 16; // 16 by 16 tile. FIXME when graphics are updated.
	final int scale = 4;                            // Scales up pixel art by 4x
	public final int tileSize = originalTileSize * scale;  //Actual size of tile is 64x64.
	final int maxScreenCol = 16;   // Number of tiles that fit on the game screen horizontally
	final int maxScreenRow = 12;   // Number of tiles that fit on the game screen vertically
	
	public final int screenWidth = tileSize * maxScreenCol;  // 1024 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 768 pixels

	//World settings (12 x 7 map)
	public final int maxWorldCol = 12; 
	public final int maxWorldRow = 6;

	// Frames per second
	int FPS = 60;
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this); // Instantiate Collision Checker
	public AssetSetter aSetter = new AssetSetter(this); // place objects on the map 
	Thread gamThread;
	public UI ui = new UI(this);

	// Entity and Object
	public Player player = new Player(this, keyH); // Make new player from Player class
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	// GAME STATE
	public int gameState;
	public final int playState = 1; 
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	/**
	 * Constructor for GamePanel
	 */
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // Drawing done in offscreen painting buffer
		this.addKeyListener(keyH);
		this.setFocusable(true);
	} // end constructor
	
	
	
	/**
	 * Setup objects, NPCs, music
	 */
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		gameState = playState;
	}
	
	
	
	/**
	 * Create and run new game thread
	 */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start(); 
	} // end startGameThread
	
	
	
	/**
	 * Run method for game
	 */
	@Override
	public void run() {
	
		double drawInterval = 1000000000/FPS; // 0.016666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;

		while(gameThread != null) {

			update();

			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	} // end run
	
	
	
	/**
	 * Update player position
	 */
	public void update() {
		if(gameState == playState) {

			//Player
			player.update();

			//NPC
			for(int i = 0; i < npc.length; i++){
				if(npc[i] != null){
					npc[i].update();
				}
			}

		}
		if(gameState == pauseState) {
			// nothing
		}
		
	} // end update

	
	
	/**
	 * Display player sprite
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		// DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}
		
		// Draw map BELOW player
		tileM.draw(g2);
		
		
		// draw objects
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

		// NPC
		for(int i = 0; i < npc.length; i++){
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		
		// Draw player
		player.draw(g2);

		//TODO for tiles that overlap player, call tileM.draw here
		
		// UI
		ui.draw(g2);
		
		// DEBUG
		if (keyH.checkDrawTime == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: "+passed);
		}

		g2.dispose();
	} // end paintComponent

	
	
	// This method plays musics and loops it
	public void playMusic(int i) {

		sound.setFile(i);
		sound.play();
		sound.loop();
	} // end playMusic

	
	
	// this method stops music
	public void stopMusic() {

		sound.stop();
	} // end stopMusic

	
	
	// this method plays sound effects
	public void playSE(int i) {
		
		sound.setFile(i);
		sound.play();
	} // end playSE
	
} // end GamePanel
