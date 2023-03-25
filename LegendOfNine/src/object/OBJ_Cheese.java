package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import edu.nmsu.cs.legendofnine.GamePanel;

public class OBJ_Cheese extends SuperObject{


	GamePanel gp;

	public OBJ_Cheese(GamePanel gp) {

		this.gp = gp;

		name = "Cheese";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/cheese2.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
