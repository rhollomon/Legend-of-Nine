package entity;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.nmsu.UtilityTool;
import edu.nmsu.cs.legendofnine.GamePanel;
import edu.nmsu.cs.legendofnine.KeyHandler;
import object.OBJ_Shield_Normal;
import object.OBJ_Sword_Normal;

public class Player extends Entity{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;

	public int numCheese = 0; // # of cheese player has picked up

	public boolean attackCancled = false;
	
	
	
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
		
		attackArea.width = 36;
		attackArea.height = 36;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		
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
		level = 1;
		maxlife = 6;
		life = maxlife;
		strength = 1; // the more strength they have, the more damage they deal.
		dex = 1; // the more dex they have, they less damage they recieve
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Normal(gp);
		atkVal = getAtk(); // the total atk value is decided by strength and weapon
		defVal = getDef(); // the total def value is decided by dex and shield
		
	} // end setDefaultValues

	public int getAtk() {
		return atkVal = strength * currentWeapon.itemAtkVal;
	}

	public int getDef() {
		return defVal = dex * currentShield.itemDefVal;
	}
	
	/**
	 * Sends player sprites to entity variables
	 * 
	 * @throws IOException
	 */
	public void getPlayerImage() {
	
		up1 = setup("/player/player_up_1" ,gp.tileSize ,gp.tileSize);
		up2 = setup("/player/player_up_2" ,gp.tileSize ,gp.tileSize);
		down1 = setup("/player/player_down_1" ,gp.tileSize ,gp.tileSize);
		down2 = setup("/player/player_down_2" ,gp.tileSize ,gp.tileSize);
		left1 =setup("/player/player_left_1" ,gp.tileSize ,gp.tileSize);
		left2 = setup("/player/player_left_2" ,gp.tileSize ,gp.tileSize);
		right1 = setup("/player/player_right_1" ,gp.tileSize ,gp.tileSize);
		right2 = setup("/player/player_right_2" ,gp.tileSize ,gp.tileSize);

	} // end getPlayerImage

	public void getPlayerAttackImage() {

		attackDown1 = setup("/player/pattack_d1" ,gp.tileSize ,gp.tileSize*2);
		attackDown2 = setup("/player/pattack_d2",gp.tileSize ,gp.tileSize*2);
		attackLeft1 = setup("/player/pattack_l1",gp.tileSize *2,gp.tileSize);
		attackLeft2 = setup("/player/pattack_l2",gp.tileSize *2,gp.tileSize);
		attackRight1 = setup("/player/pattack_r1",gp.tileSize *2,gp.tileSize);
		attackRight2 = setup("/player/pattack_r2",gp.tileSize *2,gp.tileSize);
		attackUp1 = setup("/player/pattack_u1",gp.tileSize ,gp.tileSize*2);
		attackUp2 = setup("/player/pattack_u2",gp.tileSize ,gp.tileSize*2);

	}
	
	
	
	/**
	 * Try to read and load image from name and path
	 */
	public BufferedImage setup(String imageName, int width, int height) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;    

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName+".png"));
			image = uTool.scaleImage(image, width, height);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	} // end setup


	
	/**
	 * Update player position upon keys being pressed
	 */
	public void update() {

		if (attacking == true) {
			attacking();
		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true 
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

			//CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);

			// CHECK EVENT
			gp.eHandler.checkEvent();

			// If collisionOn is false, player can move
			if (collisionOn == false && keyH.enterPressed == false){
				
				switch(direction){
				case "up":    worldY -= speed;  break;
				case "down":  worldY += speed;  break;
				case "left":  worldX -= speed;  break;
				case "right": worldX += speed;  break;
				}
			}

			if(keyH.enterPressed == true && attackCancled == false) {
				// gp.playSE(i); index i is the sound effect for attacking
				attacking = true;
				spriteCounter = 0;
			}

			attackCancled = false;

			gp.keyH.enterPressed = false;// used to for NPC dialogue w/out the need for WASD key
			
			// Tells sprite to go to the next animation every 11 frames
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) spriteNum = 2;
				else if(spriteNum == 2) spriteNum = 1;
				
				spriteCounter = 0;
			} // end animation check
			
		} // end if
		
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	} // end update

	
	public void attacking() {

		spriteCounter++;

		if(spriteCounter <=5) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;

			// Sace the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;

			// Adjust player's worldX/Y fpr the attackArea
			switch(direction) {
				case "up" : worldY -= attackArea.height; break;
				case "down" : worldY += attackArea.height; break;
				case "left" : worldX -= attackArea.width; break;
				case "right" : worldX += attackArea.width; break;
			}
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			// Check monster collision with the updated worldX, worldY, and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);

			//After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;

		}
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
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

		
		if(gp.keyH.enterPressed == true) {

			if (i != 999) {
				// Player is now colliding with an NPC
				attackCancled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}// end if
		}

	} // end interactNPC
	
	public void contactMonster(int i) {

		if(i != 999) {

			if(invincible == false) {
				// TODO gp.playSE(i); where i is the index of monster hit sound effect
				life -= 1;
				invincible = true;
			}
		}
	}

	public void damageMonster(int i) {

		if(i != 999) {
		
			if(gp.monster[i].invincible == false) {
				
				// TODO gp.playSE(i); where i is the index of monster damaged sound effect
				gp.monster[i].life -= 1;
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();

				if(gp.monster[i].life <=0) {
					gp.monster[i].dying = true;
				}
			}

		}

	}
	
	
	/**
	 * Sets player sprite based on direction, and animates extra frames while walking.
	 * 
	 * @param g2 2D graphical object
	 */
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch(direction) {
		
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) image = up1;
				if(spriteNum == 2) image = up2;
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) image = attackUp1;
				if(spriteNum == 2) image = attackUp2;
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) image = down1;
				if(spriteNum == 2) image = down2;
			}
			if(attacking == true) {
				if(spriteNum == 1) image = attackDown1;
				if(spriteNum == 2) image = attackDown2;
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) image = left1;
				if(spriteNum == 2) image = left2;
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) image = attackLeft1;
				if(spriteNum == 2) image = attackLeft2;
			}	
			break;	
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) image = right1;
				if(spriteNum == 2) image = right2;
			}
			if(attacking == true) {
				if(spriteNum == 1) image = attackRight1;
				if(spriteNum == 2) image = attackRight2;
			}
			break;
			
		} // end switch

		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		//DEBUG
		g2.setFont(new Font("Arial", Font.PLAIN, 26));
		g2.setColor(Color.white);
		g2.drawString("Invincible: " + invincibleCounter, 10, 400);
		
	} // end draw

}
