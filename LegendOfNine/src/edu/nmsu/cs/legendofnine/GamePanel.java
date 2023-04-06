package edu.nmsu.cs.legendofnine;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Primary settings for the game panel window. Includes tile size and size of the game window.
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	
	// Screen settings
	final int originalTileSize = 16; // 16 by 16 tile
	final int scale = 4;                            // Scales up pixel art by 4x
	public final int tileSize = originalTileSize * scale;  //Actual size of tile is 64x64.
	final int maxScreenCol = 16;   // Number of tiles that fit on the game screen horizontally
	final int maxScreenRow = 12;   // Number of tiles that fit on the game screen vertically
	
	public final int screenWidth = tileSize * maxScreenCol;  // 1024 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 768 pixels

	//World settings (19 x 17 map)
	public final int maxWorldCol = 19; 
	public final int maxWorldRow = 17;

	// Frames per second
	int FPS = 60;
	
	// SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this); // Instantiate Collision Checker
	public AssetSetter aSetter = new AssetSetter(this); // place objects on the map 
	public EventHandler eHandler = new EventHandler(this);
	Thread gamThread;
	public UI ui = new UI(this);

	// Entity and Object
	public Player player = new Player(this, keyH); // Make new player from Player class
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1; 
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	
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
		aSetter.setMonster();
		playMusic(0);
		gameState = titleState; // we start at TITLE 
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
			for(int i = 0; i< monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive == true && monster[i].dying == false) {
						monster[i].update();
					}
					if(monster[i].alive == false) {
						monster[i] = null;
					}
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

		// TITLE SCREEN
		if (gameState == titleState){
			ui.draw(g2);
		}

		// NOT TITLE SCREEN
		else {

			// Draw map BELOW player
			tileM.draw(g2);

			//ADD ENTITIES TO THE LIST
			entityList.add(player);

			for(int i=0; i<npc.length; i++) {
				if(npc[i]!=null){
					entityList.add(npc[i]);
				}
			}

			for(int i=0; i<obj.length; i++){
				if(obj[i]!=null){
					entityList.add(obj[i]);
				}
			}

			for(int i=0; i<monster.length; i++){
				if(monster[i]!=null){
					entityList.add(monster[i]);
				}
			}

			// SORT
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result; 
				}
			});

			// DRAW ENTITIES
			for(int i = 0; i<entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			// EMPTY ENTITY LIST
			entityList.clear();

			// UI
			ui.draw(g2);
		}
		
		
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
