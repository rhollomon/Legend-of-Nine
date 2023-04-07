package object;


import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Cheese extends Entity{

	public OBJ_Cheese(GamePanel gp) {

		super(gp);

		name = "Cheese";
		down1 = setup("/objects/cheese2");
		description = "[" + name + "]\nOne of the nine fabled cheeses.\nKeep this close.";
	}
}
