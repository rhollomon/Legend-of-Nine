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

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font pixel_font;
	Font winning_font;
	
	BufferedImage cheeseImage;
	BufferedImage itemImage;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	
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
	} // end showMessage
	
	
	/**
	 * Display UI text on screen
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		
		
		
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
			drawDialogueScreen();
		}

	} // end draw
	
	
	
	
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
