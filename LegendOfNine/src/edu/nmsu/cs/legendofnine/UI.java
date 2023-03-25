package edu.nmsu.cs.legendofnine;

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
	Font pixel_font;
	Font winning_font;
	
	BufferedImage cheeseImage;
	BufferedImage itemImage;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
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
	} // end draw

}
