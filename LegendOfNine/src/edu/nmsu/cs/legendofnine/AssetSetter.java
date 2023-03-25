package edu.nmsu.cs.legendofnine;

import object.OBJ_Cheese;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Boots;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	// Creating keys(cheese) to place on the map 
	public void setObject() {
		gp.obj[0] = new OBJ_Cheese(gp);
		gp.obj[0].worldX = 10 * gp.tileSize;
		gp.obj[0].worldY = 3 * gp.tileSize; 
		
		gp.obj[1] = new OBJ_Cheese(gp);
		gp.obj[1].worldX = 1 * gp.tileSize;
		gp.obj[1].worldY = 3 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 5 * gp.tileSize;
		gp.obj[2].worldY = 5 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Chest(gp);
		gp.obj[3].worldX = 5 * gp.tileSize;
		gp.obj[3].worldY = 1 * gp.tileSize;

		gp.obj[4] = new OBJ_Boots(gp);
		gp.obj[4].worldX = 3 * gp.tileSize;
		gp.obj[4].worldY = 1 * gp.tileSize;
		
	}

}
