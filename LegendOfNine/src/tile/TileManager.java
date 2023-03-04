package tile;

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
		tile = new Tile[10]; // Currently supports 10 different types of tiles
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
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // end getTileImage
}
