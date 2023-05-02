package object;


import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Speed_Potion extends Entity{

	public OBJ_Speed_Potion(GamePanel gp) {

		super(gp);
		type = type_consumable;
		name = "Speed Potion";
		down1 = setup("/objects/speed_potion");
		description = "[" + name + "]\nIncreases movement speed for a \nshort time.\n";
	}
}