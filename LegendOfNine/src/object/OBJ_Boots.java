package object;

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Boots extends Entity {



	public OBJ_Boots(GamePanel gp) {

		super(gp);

		name = "Boots";
		down1 = setup("/objects/boots");

    }
	
}
