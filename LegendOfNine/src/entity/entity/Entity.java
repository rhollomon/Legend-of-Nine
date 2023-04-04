package entity;
import java.awt.image.BufferedImage;
import edu.nmsu.cs.legendofnine.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import edu.nmsu.UtilityTool;
import javax.imageio.ImageIO;
/**
 * Super class for any player, monster, NPC classes. Contains sprite variables.
 * 
 * @var BufferedImage Movement sprites
 */
public class Entity {
	

	public int worldX, worldY, speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public String direction = "down";
	
	public int spriteCounter = 0;
	public int spriteNum = 1;

	// Attack Collision
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	// Tile Collision
	public Rectangle solidArea = new Rectangle(0, 0 , 48, 48);
	// Object Collision
	public int solidAreaDefaultX, solidAreaDefaultY; 

    public boolean collisionOn;

	//For NPC Creation
	GamePanel gp;
	public int actionLockCounter = 0; //used to limit the frequency of certain NPC actions
	public boolean invincible = false;
	public int invincibleCounter = 0;
	String dialogues[] = new String[20]; //array storing the series of dialogues of an NPC
	int dialogueIndex = 0; // used to traverse the dialogues array

	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	boolean attacking = false;
	public int type; // 0 = player, 1 = npc, 2 = monster

	// CHARACTER STATUS
	public int maxlife;
	public int life;

	public Entity(GamePanel gp){
		this.gp = gp;
	}


	public void setAction() {}
	public void speak() {
		// If we've exhausted the text of our NPC, restart the dialogueIndex to the start
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex ++;

		// Changes the direction of the NPC to the direction of the player
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
            direction = "up";
            break;
            case "left":
                direction = "right";
                break;
            case "right":
            direction = "left";
            break;
        }
	}
	public void update(){
		setAction();

		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);

		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				// we give damage
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}

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

		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}

	public void draw(Graphics2D g2){

		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		//Ensures tiles outside the FOV of the player are not drawn 
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
				switch(direction) {
		
					case "up":
						if(spriteNum == 1) image = up1;
						if(spriteNum == 2) image = up2;
						break;
					case "down":
						if(spriteNum == 1) image = down1;
						if(spriteNum == 2) image = down2;
						break;
					case "left":
						if(spriteNum == 1) image = left1;
						if(spriteNum == 2) image = left2;
						break;
					case "right":
						if(spriteNum == 1) image = right1;
						if(spriteNum == 2) image = right2;
						break;
						
					} // end switch

					if(invincible == true) {
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
					}

					g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			} // end if
	}

	public BufferedImage setup(String imagePath) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}