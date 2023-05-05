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
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Boots;
import object.OBJ_Cheese;
import object.OBJ_Heart;

//import com.oracle.webservices.internal.api.message.MessageContext;
//FIXME not working seemingly

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
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	//Inventory - Player
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	// Intentory - Merchant 
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;

	int subState = 0;

	// Merchant NPC
	public Entity merchant;

	
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
		cheeseImage = cheese.down1;
		
		playTime = 0;

		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_blank = heart.image3;
		heart_half = heart.image2;
	} // end constructor
	
	
	public void addMessage(String text, String item) {
		
		
		message.add(text);
		messageCounter.add(0);
		
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
				// if(messageOn) {
				// 	// Draw message
				// 	g2.drawString(message, gp.tileSize+10, gp.tileSize*5);
				
				// 	if(itemImage != null)
				// 		g2.drawImage(itemImage, gp.tileSize/2, gp.tileSize*5-25, gp.tileSize/2, gp.tileSize/2, null);
				
				// 	messageCounter++;
				
				// 	// After 120 frames hide message
				// 	if(messageCounter > 120) {
				// 		messageCounter = 0;
				// 		messageOn = false;
				// 	}

				// }
				drawMessage();
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

		// Character State
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}

		// // Options State
		// if(gp.gameState == optionsState) {
		// 	drawOptionsScreen();	
		// }

		// Game Over State
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}

		// Trade State
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
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

	public void drawMessage() {

		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));

		for(int i =0; i<message.size();i++) {

			g2.setColor(Color.black);
			g2.drawString(message.get(i), messageX+2, messageY+2);
			g2.setColor(Color.white);
			g2.drawString(message.get(i), messageX, messageY);

			int counter = messageCounter.get(i) + 1;
			messageCounter.set(i, counter);
			messageY += 50;

			if(messageCounter.get(i) > 180) {
				message.remove(i);
				messageCounter.remove(i);
			}
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
		g2.setFont(winning_font.deriveFont(Font.BOLD,48F));
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
		g2.setFont(pixel_font.deriveFont(Font.BOLD,36F));

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

		g2.setFont(pixel_font.deriveFont(Font.PLAIN,36F));
		g2.setColor(Color.white);
		x += gp.tileSize;
		y += gp.tileSize;

		//used to ensure text remains within dialogue sub window
		for(String line: currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y += 40; //displays following line below the previous line
		}
	} // end drawDialogueScreen
	
	public void drawCharacterScreen() {

		// Create a frame
		final int frameX = gp.tileSize *2;
		final int frameY = gp.tileSize *2;
		final int frameWidth = gp.tileSize+200;
		final int frameHeight = gp.tileSize*8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// Text
		g2.setColor(Color.white);
		g2.setFont(pixel_font.deriveFont(32F));

		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;

		// Names
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("EXP", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 25;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 30;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;

		// Values
		int tailX = (frameX + frameWidth) - 30;
		// reset textY
		textY = frameY + gp.tileSize;
		String value;

		value = String.valueOf(gp.player.level);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.life + "/" + gp.player.maxlife);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.strength);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.dex);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.atkVal);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.defVal);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.exp);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.coin);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 15, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 15, null);
	}
	
	/**
	 *  Display inventory screen
	 * 
	 *  @param entity - the entity whose inventory is being drawn
	 *  @param cursor 
	 */
	public void drawInventory(Entity entity, boolean cursor){


		int frameX;
		int frameY;
		int frameWidth;
		int frameHeight;
		int slotCol = 0;
		int slotRow = 0;

		if (entity == gp.player) {

			frameX = gp.tileSize * 9;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize *6 ;
			frameHeight = gp.tileSize * 5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}

		else {

			frameX = gp.tileSize;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize *6 ;
			frameHeight = gp.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		// Slots
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;

		// Draw players items
		for(int i = 0; i < entity.inventory.size(); i++){

			// Highlights the items in the player's inventory
			// which are currently equiped.
			if(entity.inventory.get(i) == entity.currentWeapon ||
				entity.inventory.get(i) == entity.currentShield){
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			   }


			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			slotX += slotSize;

			if(i == 4 || i == 9 || i == 14){
				slotX = slotXstart;
				slotY += slotSize;
			}
		}

		//Cursor 
		if (cursor == true) {
			int cursorX = slotXstart + (slotSize * slotCol); //rows of inventory; indexed 0-4
			int cursorY = slotYstart + (slotSize * slotRow); //columns of inventory; indexed 0-3
			int cursorHeight = gp.tileSize;
			int cursorWidth = gp.tileSize;
	
			//Draw cursor
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10,10);
	
			// Description Frame
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize*3;
			
	
			//Draw desciption text
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(24F));
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow); 
	
			if(itemIndex < entity.inventory.size()){
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				for(String line: entity.inventory.get(itemIndex).description.split("\n")){
					g2.drawString(line, textX, textY);
					textY += 32;
				}
			}
		}
	}

	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

		int x;
		int y;
		String text;
		g2.setFont(winning_font.deriveFont(Font.BOLD,84F));

		text = "Game Over";
		// Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text,x,y);
		// Main
		g2.setColor(Color.white);
		g2.drawString(text,x-4,y-4);

		// Retry
		g2.setFont(pixel_font.deriveFont(48F));
		text = "Retry";
		x = getXforCenteredText(text);
		y+=gp.tileSize*4;
		g2.drawString(text,x,y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}

		// Back to the title screen
		text = "Quit";
		x=getXforCenteredText(text);
		y+=55;
		g2.drawString(text,x,y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
	}

	public void drawTradeScreen() {
		switch(subState) {
		case 0: tradeSelect();break;
		case 1: tradeBuy();break;
		case 2: tradeSell();break;
		}
		gp.keyH.enterPressed = false;

	}

	public void tradeSelect() {

		// Display dialogue of merchant npc
		drawDialogueScreen();

		// Draw Selection window
		int x = gp.tileSize * 10;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 3;
		int height = (int) (gp.tileSize * 3.5);
		drawSubWindow(x,y,width,height);

		// Draw selection text
		y += gp.tileSize;
		x += gp.tileSize;
		g2.drawString("Buy",x,y);
		if (commandNum == 0) {
			g2.drawString(">", x-24, y);

			// Pressing enter on buy
			if (gp.keyH.enterPressed == true) {
				subState=1;
			}
		}
		y += gp.tileSize;

		g2.drawString("Sell",x,y);
		if (commandNum == 1) {
			g2.drawString(">", x-24, y);

			// Pressing enter on sell
			if (gp.keyH.enterPressed == true) {
				subState=2;
			}
		}
		y += gp.tileSize;

		g2.drawString("Leave",x,y);
		if (commandNum == 2) {
			g2.drawString(">", x-24, y);

			// Pressing enter on leave
			if (gp.keyH.enterPressed == true) {
				commandNum=0;
				gp.gameState = gp.dialogueState;
				currentDialogue = "Come again!";
			}
		}

		
	}
	public void tradeBuy() {

		// Draw player inventory
		drawInventory(gp.player, false);

		// Draw merchant inventory
		drawInventory(merchant, true);

		// draw hint window
		int x = gp.tileSize;
		int y = gp.tileSize * 10;
		int width = gp.tileSize * 3;
		int height = (int) (gp.tileSize);
		drawSubWindow(x,y,width,height);
		g2.drawString(" [ESC] Back", x+24, y+45);

		// draw player currency window
		x = gp.tileSize * 10;
		y = gp.tileSize * 10;
		width = gp.tileSize * 4;
		height = (int) (gp.tileSize);
		drawSubWindow(x,y,width,height);
		g2.drawString(" Cheese Count: " + gp.player.numCheese , x+24, y+45);

		// draw price window
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if (itemIndex < merchant.inventory.size()) {
			
			x = (int) (gp.tileSize*5.5);
			y = (int) (gp.tileSize*5.5);
			width = (int) (gp.tileSize*2);
			height = (int) (gp.tileSize);
			drawSubWindow(x,y,width,height);
			g2.drawImage(cheeseImage,x+18,y+18,32,32,null);

			int price = merchant.inventory.get(itemIndex).price;
			String text = ""+price;
			x = getXforAllignToRightText(text, gp.tileSize*7);
			g2.drawString(text, x, y+42);

			// buying items
			if (gp.keyH.enterPressed == true) {

				// not enough cheese - player inventory
				if (merchant.inventory.get(itemIndex).price > gp.player.numCheese) {
					subState=0;
					gp.gameState=gp.dialogueState;
					currentDialogue = "More Cheese Needed!";
					drawDialogueScreen();
				}
				// not enough inventory space - player
				else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
					subState=0;
					gp.gameState=gp.dialogueState;
					currentDialogue = "Inventory Full!";
					drawDialogueScreen();
				}
				else {
					gp.player.numCheese -= merchant.inventory.get(itemIndex).price;
					gp.player.inventory.add(merchant.inventory.get(itemIndex));
				}
			}

		}


	}
	public void tradeSell() {

		// Draw player inventory
		drawInventory(gp.player, true);

		// draw hint window
		int x = gp.tileSize;
		int y = gp.tileSize * 10;
		int width = gp.tileSize * 3;
		int height = (int) (gp.tileSize);
		drawSubWindow(x,y,width,height);
		g2.drawString(" [ESC] Back", x+24, y+45);

		// draw player currency window
		x = gp.tileSize * 10;
		y = gp.tileSize * 10;
		width = gp.tileSize * 4;
		height = (int) (gp.tileSize);
		drawSubWindow(x,y,width,height);
		g2.drawString(" Cheese Count: " + gp.player.numCheese , x+24, y+45);

		// draw price window
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if (itemIndex < gp.player.inventory.size()) {
			
			x = (int) (gp.tileSize*13.5);
			y = (int) (gp.tileSize*5.5);
			width = (int) (gp.tileSize*2);
			height = (int) (gp.tileSize);
			drawSubWindow(x,y,width,height);
			g2.drawImage(cheeseImage,x+18,y+18,32,32,null);

			int price = gp.player.inventory.get(itemIndex).price;
			String text = ""+price;
			x = getXforAllignToRightText(text, gp.tileSize*15);
			g2.drawString(text, x, y+42);

			// selling items
			if (gp.keyH.enterPressed == true) {

				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
				|| gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "Cannot sell an equipped item!";
				}
				else {
					gp.player.inventory.remove(itemIndex);
					gp.player.numCheese += price;
				}

			}
		}
	}

	public int getItemIndexOnSlot(int slotCol, int slotRow){
		int itemIndex = slotCol + (slotRow * 5);
		return itemIndex;
	}



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
		//g2.fillRoundRect(x, y, width, height, 35,35);
		g2.fillRect(x, y, width+4, height+4);
		c = new Color(218,165,32); //gold
		g2.setColor(c);
		g2.setStroke(new BasicStroke(4)); //defines the width of the border surrounding sub window
		//g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
		g2.drawRect(x+8, y+8, width - 12, height - 12);
	} // end drawSubWindow

	public int getXforAllignToRightText(String text, int tailX) {

		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;

	}
}
