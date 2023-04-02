package object;


import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Cheese extends Entity{

	public OBJ_Cheese(GamePanel gp) {

		super(gp);

		name = "Cheese";
		down1 = setup("/objects/cheese2");
	}
}
