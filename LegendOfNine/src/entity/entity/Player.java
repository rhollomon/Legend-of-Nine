package entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.nmsu.cs.legendofnine.GamePanel;
import edu.nmsu.cs.legendofnine.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;

	int numCheese = 0; // # of cheese player has picked up
	/**
	 * Constructor for Player class
	 * 
	 * @param gp Game Panel
	 * @param keyH KeyHandler
	 */
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
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
		
	} // end setDefaultValues
	
	
	
	/**
	 * Sends player sprites to entity variables
	 * 
	 * @throws IOException
	 */
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // end getPlayerImage
	
	
	
	/**
	 * Update player position upon keys being pressed
	 */
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true 
	    || keyH.leftPressed == true || keyH.rightPressed == true) {
			
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

			// If collisionOn is false, player can move
			if (collisionOn == false){
				
				switch(direction){
				case "up":    worldY -= speed;  break;
				case "down":  worldY += speed;  break;
				case "left":  worldX -= speed;  break;
				case "right": worldX += speed;  break;
				}
			}
			
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
					numCheese++; 
					gp.obj[i] = null;

					System.out.println("Cheese:"+numCheese);
				break;

				case "Chest":
					System.out.println("Chest");
				break;

				case "Door":
					if (numCheese > 0) {
						gp.obj[i] = null;
						numCheese--;
					}
				break;
			}
		}
	} // end pickupObject
	
	
	
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
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	} // end draw

}
