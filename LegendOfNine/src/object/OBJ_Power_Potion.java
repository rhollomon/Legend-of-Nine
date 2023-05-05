package object;


import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Power_Potion extends Entity{

	public OBJ_Power_Potion(GamePanel gp) {

		super(gp);
		type = type_consumable;
		name = "Power Potion";
		down1 = setup("/objects/power_potion");
		description = "[" + name + "]\nIncreases damage dealt for a \nshort time.\n";
		price=1;
	}
}