package object;


import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Key extends Entity{

	public OBJ_Key(GamePanel gp) {

		super(gp);

		name = "Key";
		down1 = setup("/objects/cheese2");
		description = "[" + name + "]\nSome sort of key.\nLikely for a chest.";
	}
}
