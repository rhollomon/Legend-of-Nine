package object;

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Shield_Stronger extends Entity {

    public OBJ_Shield_Stronger(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Iron Shield";
        down1 = setup("/objects/fancyshield");
        itemDefVal = 10;
        description = "[" + name + "]\nShield meant to block incoming\nattacks. Defends against more \npowerful attacks.";
    }
    
}
