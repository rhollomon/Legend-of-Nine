package object;

// place chest at (5,1)

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Chest extends Entity{

	public OBJ_Chest(GamePanel gp) {

		super(gp);

		name = "Chest";
		down1 = setup("/objects/chest");
		collision = true;
	}
}
