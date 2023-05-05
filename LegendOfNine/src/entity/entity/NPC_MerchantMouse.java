package entity;
import edu.nmsu.cs.legendofnine.GamePanel;

import object.OBJ_Shield_Stronger;
import object.OBJ_Power_Potion;
import object.OBJ_HP_Potion;

import object.OBJ_Shield_Normal;


public class NPC_MerchantMouse extends Entity{

    public NPC_MerchantMouse(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1; 

        getImage();  
        setDialogue();
        setItems();
    }
    /**
	 * Sends player sprites to superclass entity variables
	 * 
	 * @throws IOException
	 */ 
	public void getImage() {
	
		up1 = setup("/npc/oldmouse_up_1");
		up2 = setup("/npc/oldmouse_up_2");
		down1 = setup("/npc/oldmouse_down_1");
		down2 = setup("/npc/oldmouse_down_2");
		left1 =setup("/npc/oldmouse_left_1");
		left2 = setup("/npc/oldmouse_left_2");
		right1 = setup("/npc/oldmouse_right_1");
		right2 = setup("/npc/oldmouse_right_2");

	} // end getImage

    /**
     * set Dialogue options for NPC
     *
     */ 
    public void setDialogue() {

        dialogues[0] = "Hello young mouse, want to trade?";
    }
    
    /**
     * Set inventory items for NPC
     */
    public void setItems() {

        inventory.add(new OBJ_Shield_Stronger(gp));
        inventory.add(new OBJ_Shield_Normal(gp));
        inventory.add(new OBJ_Power_Potion(gp));
        inventory.add(new OBJ_HP_Potion(gp));

    }

    public void speak() {

        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.merchant = this; // allow UI class access to merchant inventory

    }
}
