package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

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

	public Rectangle solidArea;

    public boolean collisionOn;

}