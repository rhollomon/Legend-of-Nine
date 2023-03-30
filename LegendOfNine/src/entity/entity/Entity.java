package entity;
import java.awt.image.BufferedImage;
import edu.nmsu.cs.legendofnine.GamePanel;

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
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;

	// Tile Collision
	public Rectangle solidArea = new Rectangle(0, 0 , 48, 48);
	// Object Collision
	public int solidAreaDefaultX, solidAreaDefaultY; 

    public boolean collisionOn;

	//For NPC Creation
	GamePanel gp;
	public int actionLockCounter = 0; //used to limit the frequency of certain NPC actions

	public Entity(GamePanel gp){
		this.gp = gp;
	}


	public void setAction(){

	}

	public void update(){
		setAction();

		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);

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