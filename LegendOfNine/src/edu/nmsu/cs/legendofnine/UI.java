package edu.nmsu.cs.legendofnine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.OBJ_Boots;
import object.OBJ_Cheese;
import object.OBJ_Heart;
import object.SuperObject;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font pixel_font;
	Font winning_font;
	
	BufferedImage cheeseImage;
	BufferedImage itemImage;
	BufferedImage heart_full;
	BufferedImage heart_half;
	BufferedImage heart_blank;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	/**
	 * @param gp
	 */
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/DisposableDroidBB.ttf");
			pixel_font = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/PixelFJVerdana12pt.ttf");
			winning_font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Load cheese image from OBJ_Cheese
		OBJ_Cheese cheese = new OBJ_Cheese(gp);
		cheeseImage = cheese.image;
		
		playTime = 0;

		// CREATE HUD OBJECT
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_blank = heart.image3;
		heart_half = heart.image2;
	} // end constructor
	
	
	
	public void showMessage(String text, String item) {
		message = text;
		messageOn = true;
		
		if(item != null) {
			switch(item) {
			case "boots":
				OBJ_Boots boots = new OBJ_Boots(gp);
				itemImage = boots.image;
				break;
				
			default:
				itemImage = null;
				break;
			} // end switch
		} // end if
		else { itemImage = null; }
		
	} // end showMessage
	
	
	/**
	 * Display UI text on screen
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		// If we are in TITLE state
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		if(gp.gameState == gp.playState) {
			if(gameFinished) {
				g2.setFont(pixel_font.deriveFont(Font.PLAIN, 36F));
				g2.setColor(Color.white);
			
				int textLength, x, y;
				String text = "You win!";
			
				// Returns length of text in pixels
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
				// Centers text on screen
				x = gp.screenWidth / 2 - (textLength/2);
				y = gp.screenHeight / 2 - (gp.tileSize*3);
			
				g2.drawString(text, x, y);
			
				// ----------------------------------

				g2.setFont(pixel_font.deriveFont(Font.PLAIN, 24F));
			
				text = "Time: " + dFormat.format(playTime);
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				x = gp.screenWidth / 2 - (textLength/2);
				y = gp.screenHeight / 2 - (gp.tileSize*4+20);
			
				g2.drawString(text, x, y);
			
				// ----------------------------------
			
				g2.setFont(pixel_font.deriveFont(Font.PLAIN, 72F));
				g2.setColor(Color.yellow);
			
				text = "Congratulations!";
				// Returns length of text in pixels
				textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
				x = gp.screenWidth / 2 - (textLength/2);
				y = gp.screenHeight / 2 - (gp.tileSize*2);
			
				g2.drawString(text, x, y);
			
				// End game thread
				gp.gameThread = null;
			
			
			} else { // typical gameplay behavior
				g2.setFont(pixel_font.deriveFont(Font.PLAIN, 36F));
				g2.setColor(Color.white);

				drawPlayerLife();
			
				g2.drawImage(cheeseImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, null);
			
				// Write string at position 50,50
				g2.drawString("collected: "+gp.player.numCheese, 75, 55);
			
				//TIMER, called 60 times per second
				playTime += 1.0/60;
				g2.drawString("Time: "+dFormat.format(playTime), gp.screenWidth-175, 55);
			
				// Display notification message for 2 seconds
				if(messageOn) {
					// Draw message
					g2.drawString(message, gp.tileSize+10, gp.tileSize*5);
				
					if(itemImage != null)
						g2.drawImage(itemImage, gp.tileSize/2, gp.tileSize*5-25, gp.tileSize/2, gp.tileSize/2, null);
				
					messageCounter++;
				
					// After 120 frames hide message
					if(messageCounter > 120) {
						messageCounter = 0;
						messageOn = false;
					}

				}
			} // end else
		} // end play state 
		
		// Pause State
		if(gp.gameState == gp.pauseState){
			drawPauseScreen();
		}

		//Dialogue State
		if(gp.gameState == gp.dialogueState){
			drawPlayerLife();
			drawDialogueScreen();
		}

	} // end draw
	
	/*
	 *  Draw Plater life when the method is called
	 */
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2 + 35; // drawing it right under the # of cheese collected
		int i = 0;

		// DRAW MAX LIFE
		while(i<gp.player.maxlife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}

		// RESSET
		x = gp.tileSize/2;
		y = gp.tileSize/2 + 35; // drawing it right under the # of cheese collected
		i = 0;

		// DRAW CURRENT LIFE
		while (i < gp.player.life) {
			g2.drawImage(heart_half,x, y, null);
			i++;
			if(i<gp.player.life) {
				g2.drawImage(heart_full,x,y,null);	
			}
			i++;
			x += gp.tileSize;
		}
	}

	/**
	 * Draw Title screen when in gp.titleState
	 */
	public void drawTitleScreen() {
		
		// BACKGROUND COLOR
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

		// FONT
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		// TEXT
		String text = "Legend of Nine";

		// LOCATION
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;

		// TEXT SHADOW
		g2.setColor(Color.gray);
		g2.drawString(text, x+3, y+3);

		// TEXT COLOR 
		g2.setColor(Color.white);
		g2.drawString(text, x, y);

		// MOUSE SPRIT
		x = gp.screenWidth/2 - (gp.tileSize); 
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0){
			g2.drawString(">", x-gp.tileSize,y);
		}

		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1){
			g2.drawString(">", x-gp.tileSize,y);
		}

	}
	/**
	 * Display pause screen UI
	 */
	public void drawPauseScreen() {
		
	 g2.setFont(pixel_font.deriveFont(Font.PLAIN, 40F));
	 g2.setColor(Color.white);
	 String text = "PAUSED";
	 int x = getXforCenteredText(text);
	 
	 int y = gp.screenHeight/2; 
	 g2.drawString(text,x, y);
	} // end drawPauseScreen
	
	
	
	
	/**
	 * Generate x value for centering text on screen
	 * 
	 * @param text to generate x value for
	 * @return x value
	 */
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
		
	} // getXforCenteredText

	
	
	
	/**
	 * Display dialogue window UI
	 */
	public void drawDialogueScreen(){

		//Defining dimensions of sub window and creation
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize * 3;
		drawSubWindow(x,y,width,height);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		x += gp.tileSize;
		y += gp.tileSize;

		//used to ensure text remains within dialogue sub window
		for(String line: currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y += 40; //displays following line below the previous line
		}
	} // end drawDialogueScreen
	
	
	
	/**
	 * Draw sub window; arguments are parameters of window
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawSubWindow(int x, int y, int width, int height){
		Color c = new Color(0,0,0, 200); //black, with slight transparency
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35,35);
		c = new Color(218,165,32); //gold
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5)); //defines the width of the border surrounding sub window
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	} // end drawSubWindow
}
