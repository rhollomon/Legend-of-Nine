package object;

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Shield_Normal extends Entity {

    public OBJ_Shield_Normal(GamePanel gp) {
        super(gp);
        
        
        name = "Wood Shield";
        down1 = setup("/objects/shield_blue");
        itemDefVal = 1;
    }
    
}
