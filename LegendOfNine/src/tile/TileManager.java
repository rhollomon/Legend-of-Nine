package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
		
		tile = new Tile[20]; // Currently supports 20 different types of tiles
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //determined by World Settings in GamePanel.java
		
		getTileImage();
		loadMap("/maps/maptest.txt");
		
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
