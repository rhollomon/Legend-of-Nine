package object;

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_Excalibur extends Entity {

    public OBJ_Excalibur(GamePanel gp) {
        super(gp);
        
        type = type_sword;
        name = "Excalibur";
        down1 = setup("/objects/fancysword");
        itemAtkVal = 50;

        //Defines the range of the weapon
        attackArea.width = 38;
        attackArea.height = 38;
        
        description = "[" + name + "]\nSword of myth and fairytale.\nOnly the most worthy mouse can\nwield such a weapon.";
    }
    
}