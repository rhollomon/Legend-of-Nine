package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import edu.nmsu.UtilityTool;
import edu.nmsu.cs.legendofnine.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	
	/**
	 * Constructor for TileManager class
	 */
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[52]; // Currently supports 52 different types of tiles
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //determined by World Settings in GamePanel.java
		
		getTileImage();
		loadMap("/maps/hubarea.txt");
		
	} // end constructor
	
	
	
	/**
	 * Loads map for use by draw().
	 * 
	 * @param filePath File location that map is currently stored
	 */
	public void loadMap(String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) { //determined by World Settings in GamePanel.java
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				} // end while
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				} // end if
				
			} // end while
			
			br.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Load tiles for map into tile array
	 * 
	 * @var tile of type Tile[]
	 */
	public void getTileImage() {
		

		// TESTING AREA TILES 0-10
		setup(0, "grass1", false);
		setup(1, "grass2", false);
		setup(2, "grass3", false);
		setup(3, "fencebottom", true);
		setup(4, "fenceleft", true);
		setup(5, "fenceright", true);
		setup(6, "fencetop", true);
		setup(7, "fence_top_left", true);
		setup(8, "fence_top_right", true);
		setup(9, "fence_bottom_left", true);
		setup(10, "fence_bottom_right", true);
		
		// HUB AREA TILES
		// Corners 12-15
		// Inverse corners 16-19
		// Left walls 20-21
		// Right walls 22-23
		// Top walls 24-27
		// Bottom walls 28-31
		// Floor edges (clockwise) 32-40
		// Floor centers 41-44
		// Tutorial 45-51
		setup(11, "dummy_tile", false);
		setup(12, "wallleftuppercorner", true);
		setup(13, "wallrightuppercorner", true);
		setup(14, "wallleftbottomcorner", true);
		setup(15, "wallrightbottomcorner", true);
		setup(16, "wallturnleft1", true);
		setup(17, "wallturnleft2", true);
		setup(18, "wallturnright1", true);
		setup(19, "wallturnright2", true);
		setup(20, "wallleft1", true);
		setup(21, "wallleft2", true);
		setup(22, "wallright1", true);
		setup(23, "wallright2", true);
		setup(24, "wallupper1", true);
		setup(25, "wallupper2", true);
		setup(26, "wallupper3", true);
		setup(27, "wallupper4", true);
		setup(28, "wallbottom1", true);
		setup(29, "wallbottom2", true);
		setup(30, "wallbottom3", true);
		setup(31, "wallbottom4", true);
		setup(32, "floorupperleft", false);
		setup(33, "floorupper1", false);
		setup(34, "floorupper2", false);
		setup(35, "floorupperright", false);
		setup(36, "floorright", false);
		setup(37, "floorbottomright", false);
		setup(38, "floorbottom1", false);
		setup(39, "floorbottom2", false);
		setup(40, "floorbottomleft", false);
		setup(41, "floor1", false);
		setup(42, "floor2", false);
		setup(43, "floor3", false);
		setup(44, "floor4", false);
		setup(45, "tutorial1", false);
		setup(46, "tutorial2", false);
		setup(47, "tutorial3", false);
		setup(48, "tutorial4", false);
		setup(49, "tutorial5", false);
		setup(50, "tutorial6", false);
		setup(51, "tutorial7", false);
	} // end 

	
	
	
	public void setup(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;

		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * Draws currently loaded map.
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		
		// Helps position tiles on map
		int worldCol = 0;
		int worldRow = 0;
		
		// Draws map
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) { //determined by World Settings in GamePanel.java
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			//Position of the player's character on screen
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			//Used to draw the tiles where the player will always be shown at the
			//center of the screen
			int screenX = worldX - gp.player.worldX + gp.player.screenX; 
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//Ensures tiles outside the FOV of the player are not drawn 
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
					g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				} // end if
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) { // if we've reached end of row
			worldCol = 0;
			worldRow++;
			} // end if
			
		} // end while
		
	} // end draw
}
