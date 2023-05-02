package object;

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal");
        itemAtkVal = 1;

        //Defines the range of the weapon
        attackArea.width = 36;
        attackArea.height = 36;
        
        description = "[" + name + "]\nSharp but ordinary blade.";
    }
    
}