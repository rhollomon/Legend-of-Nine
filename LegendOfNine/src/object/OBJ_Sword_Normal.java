package object;

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal");
        itemAtkVal = 1;
        description = "[" + name + "]\nSharp but ordinary blade.";
    }
    
}