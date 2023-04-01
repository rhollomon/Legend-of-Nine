package entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import edu.nmsu.UtilityTool;
import edu.nmsu.cs.legendofnine.GamePanel;
import edu.nmsu.cs.legendofnine.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;

	public int numCheese = 0; // # of cheese player has picked up
	
	
	
	/**
	 * Constructor for Player class
	 * 
	 * @param gp Game Panel
	 * @param keyH KeyHandler
	 */
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp); //inherits the gamepanel of the superclass in Entity.java
		this.keyH = keyH;

		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		/*
		* Collison detection - solid area of player entity
		*/ 
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		// Copy default solid area to new instance, for OBJECT collision
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
		
	} // end constructor
	
	
	
	/**
	 * Set default values of entity class for player
	 */
	public void setDefaultValues() {
		
		// Set spawn coordinates
		worldX = gp.tileSize * 7;
		worldY = gp.tileSize * 3;
		
		speed = 4;
		direction = "down";

		// PLAYER STATUS	
		maxlife = 6;
		life = maxlife;
		
	} // end setDefaultValues
	
	
	
	/**
	 * Sends player sprites to entity variables
	 * 
	 * @throws IOException
	 */
	public void getPlayerImage() {
	
		up1 = setup("/player/player_up_1");
		up2 = setup("/player/player_up_2");
		down1 = setup("/player/player_down_1");
		down2 = setup("/player/player_down_2");
		left1 =setup("/player/player_left_1");
		left2 = setup("/player/player_left_2");
		right1 = setup("/player/player_right_1");
		right2 = setup("/player/player_right_2");

	} // end getPlayerImage
	
	
	
	/**
	 * Try to read and load image from name and path
	 */
	public BufferedImage setup(String imageName) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName+".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	} // end setup


	
	/**
	 * Update player position upon keys being pressed
	 */
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true 
	    || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			
			if(keyH.upPressed == true) { // If W is pressed
	   			direction = "up";
			}
			if(keyH.downPressed == true) { // if S is pressed
				direction = "down";
			}
			if(keyH.leftPressed == true) { // if A is pressed
				direction = "left";
			}
			if(keyH.rightPressed == true) { // If D is pressed
				direction = "right";
			}

			// Check collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickupObject(objIndex);

			//Check NPC Collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// If collisionOn is false, player can move
			if (collisionOn == false && keyH.enterPressed == false){
				
				switch(direction){
				case "up":    worldY -= speed;  break;
				case "down":  worldY += speed;  break;
				case "left":  worldX -= speed;  break;
				case "right": worldX += speed;  break;
				}
			}

			gp.keyH.enterPressed = false;// used to for NPC dialogue w/out the need for WASD key
			
			// Tells sprite to go to the next animation every 11 frames
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) spriteNum = 2;
				else if(spriteNum == 2) spriteNum = 1;
				
				spriteCounter = 0;
			} // end animation check
			
		} // end if
		
	} // end update

	
	
	
	/**
	 * Given index of object, get object name and perform relative action
	 * 
	 * @param index gp index of object
	 */
	public void pickupObject(int i) {

		if (i != 999) {

			String objectName = gp.obj[i].name;

			switch (objectName) {
				case "Cheese":
					//gp.playSE(1); // make sure cheese sound is index 1
					numCheese++; 
					gp.obj[i] = null;

					//System.out.println("Cheese:"+numCheese);
				break;

				case "Chest":
					//gp.playSE(2); // make sure chest sound is index 2
										
					if(numCheese < 2) {
						gp.ui.showMessage("You need "+(2-numCheese)+" more cheese", null);
					} else {
						gp.ui.gameFinished = true;
						gp.stopMusic();
						//TODO play winning sound effect here
					}
						
				break;

				case "Door":
					//gp.playSE(3); // make sure door sound is index 3
					//if (numCheese > 0) { //TODO make it a key instead
						gp.obj[i] = null;
						//numCheese--;
					//}
					gp.ui.showMessage("Opened the door!", null);
				break;

				case "Boots": 
					//gp.playSE(4); // make sure powerup sound is index 4
					speed+=1;
					gp.obj[i]=null;
					
					gp.ui.showMessage("Picked up boots! (+1 speed)", "boots");
					break;
			} // end switch
		} // end if
	} // end pickupObject
	
	
	

	/**
	 * NPC interaction upon collision
	 * 
	 * @param i If not set to 999, play NPC interaction
	 */
	public void interactNPC(int i){

		if (i != 999) {
			// Player is now colliding with an NPC

			if(gp.keyH.enterPressed == true){
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		
		//No longer colliding with player, disable dialogue
		gp.keyH.enterPressed = false;
	} // end interactNPC
	
	
	
	
	/**
	 * Sets player sprite based on direction, and animates extra frames while walking.
	 * 
	 * @param g2 2D graphical object
	 */
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if(spriteNum == 1)
				image = up1;
			if(spriteNum == 2)
				image = up2;
			break;
			
		case "down":
			if(spriteNum == 1)
				image = down1;
			if(spriteNum == 2)
				image = down2;
			break;
			
		case "left":
			if(spriteNum == 1)
				image = left1;
			if(spriteNum == 2)
				image = left2;
			break;
			
		case "right":
			if(spriteNum == 1)
				image = right1;
			if(spriteNum == 2)
				image = right2;
			break;
			
		} // end switch
		
		g2.drawImage(image, screenX, screenY, null);
		
	} // end draw

}
