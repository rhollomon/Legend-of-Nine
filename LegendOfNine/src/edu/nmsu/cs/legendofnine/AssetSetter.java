package edu.nmsu.cs.legendofnine;

import object.OBJ_Cheese;
import object.OBJ_Chest;
import object.OBJ_Door;
import edu.nmsu.cs.legendofnine.monster.MON_Bat;
import entity.NPC_OldMouse;
import entity.NPC_MerchantMouse;
import object.OBJ_Boots;

// Potions
import object.OBJ_HP_Potion;
import object.OBJ_Power_Potion;
import object.OBJ_Speed_Potion;

// Equipment Pickups
import object.OBJ_Excalibur;
import object.OBJ_Shield_Stronger;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	// Creating keys(cheese) to place on the map 
	public void setObject() {
		
		gp.obj[0] = new OBJ_Cheese(gp);
		gp.obj[0].worldX = 54 * gp.tileSize;
		gp.obj[0].worldY = 31 * gp.tileSize; 
		
		gp.obj[1] = new OBJ_Cheese(gp);
		gp.obj[1].worldX = 57 * gp.tileSize;
		gp.obj[1].worldY = 6 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Boots(gp);
		gp.obj[4].worldX = 39 * gp.tileSize;
		gp.obj[4].worldY = 35 * gp.tileSize;
		
		/*
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 5 * gp.tileSize;
		gp.obj[2].worldY = 5 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Chest(gp);
		gp.obj[3].worldX = 5 * gp.tileSize;
		gp.obj[3].worldY = 1 * gp.tileSize;
		
		//Items implemented in game alongside inventory system
		gp.obj[5] = new OBJ_HP_Potion(gp);
		gp.obj[5].worldX = 6 * gp.tileSize;
		gp.obj[5].worldY = 1 * gp.tileSize;

		gp.obj[6] = new OBJ_Power_Potion(gp);
		gp.obj[6].worldX = 7 * gp.tileSize;
		gp.obj[6].worldY = 1 * gp.tileSize;

		gp.obj[7] = new OBJ_Speed_Potion(gp);
		gp.obj[7].worldX = 8 * gp.tileSize;
		gp.obj[7].worldY = 1 * gp.tileSize;

		gp.obj[8] = new OBJ_Excalibur(gp);
		gp.obj[8].worldX = 1 * gp.tileSize;
		gp.obj[8].worldY = 1 * gp.tileSize;

		gp.obj[9] = new OBJ_Shield_Stronger(gp);
		gp.obj[9].worldX = 2 * gp.tileSize;
		gp.obj[9].worldY = 1 * gp.tileSize;
		*/

	}

	public void setNPC(){

		gp.npc[0] = new NPC_OldMouse(gp);
		gp.npc[0].worldX = gp.tileSize * 25;
		gp.npc[0].worldY = gp.tileSize * 28;

		gp.npc[1] = new NPC_MerchantMouse(gp);
		gp.npc[1].worldX = gp.tileSize * 29;
		gp.npc[1].worldY = gp.tileSize * 35;
		
	}

	public void setMonster() {

		int i=0;

		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 50 * gp.tileSize;
		gp.monster[i].worldY = 28 * gp.tileSize;
		i++;
		
		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 49 * gp.tileSize;
		gp.monster[i].worldY = 29 * gp.tileSize;
		i++;

		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 49 * gp.tileSize;
		gp.monster[i].worldY = 28 * gp.tileSize;
		i++;

		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 50 * gp.tileSize;
		gp.monster[i].worldY = 29 * gp.tileSize;
		i++;

		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 50 * gp.tileSize;
		gp.monster[i].worldY = 10 * gp.tileSize;
		i++;
		
		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 50 * gp.tileSize;
		gp.monster[i].worldY = 11 * gp.tileSize;
		i++;
		
		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 50 * gp.tileSize;
		gp.monster[i].worldY = 12 * gp.tileSize;
		i++;
		
		gp.monster[i] = new MON_Bat(gp);
		gp.monster[i].worldX = 50 * gp.tileSize;
		gp.monster[i].worldY = 14 * gp.tileSize;
		i++;


	}

}
