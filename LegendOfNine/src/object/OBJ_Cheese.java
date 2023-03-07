package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Cheese extends SuperObject{

	public OBJ_Cheese() {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/cheese2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
