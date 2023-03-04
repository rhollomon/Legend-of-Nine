package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import edu.nmsu.cs.legendofnine.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	
	/**
	 * Constructor for TileManager class
	 */
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[20]; // Currently supports 20 different types of tiles
		mapTileNum = new int[12][7]; // For a 12 by 7 map
		
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
			
			while(col < 12 && row < 7) { // for 12 by 7 map
				
				String line = br.readLine();
				
				while(col < 12) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				} // end while
				
				if(col == 12) {
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
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass3.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fencebottom.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fenceleft.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fenceright.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fencetop.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence_top_left.png"));
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence_top_right.png"));
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence_bottom_left.png"));
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fence_bottom_right.png"));
			
			
		} catch(IOException e) {
			e.printStackTrace();
		} // end catch
	} // end 

	
	
	/**
	 * Draws currently loaded map.
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		
		// Helps position tiles on map
		int col = 0;
		int row = 0;
		
		// Position of top left tile
		int x = gp.tileSize * 2;
		int y = gp.tileSize * 2;
		
		// Draws map TODO currently a placeholder
		while(col < 12 && row < 7) { // for 12 by 7 map
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x = x + gp.tileSize;
			
			if(col == 12) { // if we've reached end of row
				col = 0;
				x = gp.tileSize * 2;
				row++;
				y = y + gp.tileSize;
			} // end if
			
		} // end while
		
	} // end draw
}
