package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import edu.nmsu.cs.legendofnine.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	
	
	/**
	 * Constructor for TileManager class
	 */
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tile = new Tile[20]; // Currently supports 20 different types of tiles
		getTileImage();
		
	} // end constructor
	
	
	
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

	
	
	public void draw(Graphics2D g2) {
		
		// Helps position tiles on map
		int tileLoc = gp.tileSize;
		
		// Draws map TODO currently a placeholder
		// TODO fix this awful spaghetti code; optimize map drawing

		// Draw top fence
		g2.drawImage(tile[7].image, tileLoc*2, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*3, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*4, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*5, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*6, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*7, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*8, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*9, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*10, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*11, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[6].image, tileLoc*12, tileLoc*2, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[8].image, tileLoc*13, tileLoc*2, gp.tileSize, gp.tileSize, null);
		
		
		// Draw bottom fence
		g2.drawImage(tile[9].image, tileLoc*2, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*3, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*4, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*5, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*6, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*7, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*8, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*9, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*10, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*11, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[3].image, tileLoc*12, tileLoc*8, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[10].image, tileLoc*13, tileLoc*8, gp.tileSize, gp.tileSize, null);
		
		
		// Draw left fence
		g2.drawImage(tile[4].image, tileLoc*2, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[4].image, tileLoc*2, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[4].image, tileLoc*2, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[4].image, tileLoc*2, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[4].image, tileLoc*2, tileLoc*7, gp.tileSize, gp.tileSize, null);
		

		// Draw right fence
		g2.drawImage(tile[5].image, tileLoc*13, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[5].image, tileLoc*13, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[5].image, tileLoc*13, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[5].image, tileLoc*13, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[5].image, tileLoc*13, tileLoc*7, gp.tileSize, gp.tileSize, null);
		
		
		g2.drawImage(tile[0].image, tileLoc*3, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*4, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*5, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*6, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*7, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*8, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*9, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*10, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*11, tileLoc*3, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*12, tileLoc*3, gp.tileSize, gp.tileSize, null);		

		
		g2.drawImage(tile[2].image, tileLoc*3, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*4, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*5, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*6, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*7, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*8, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*9, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*10, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*11, tileLoc*4, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*12, tileLoc*4, gp.tileSize, gp.tileSize, null);

		
		g2.drawImage(tile[0].image, tileLoc*3, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*4, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*5, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*6, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*7, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*8, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*9, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*10, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*11, tileLoc*5, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*12, tileLoc*5, gp.tileSize, gp.tileSize, null);
		

		g2.drawImage(tile[0].image, tileLoc*3, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*4, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*5, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*6, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*7, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*8, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*9, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*10, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*11, tileLoc*6, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*12, tileLoc*6, gp.tileSize, gp.tileSize, null);	

		
		g2.drawImage(tile[2].image, tileLoc*3, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*4, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*5, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*6, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*7, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*8, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, tileLoc*9, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*10, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, tileLoc*11, tileLoc*7, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, tileLoc*12, tileLoc*7, gp.tileSize, gp.tileSize, null);

	} // end draw
}
