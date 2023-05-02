package object;


import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class OBJ_HP_Potion extends Entity{

	GamePanel gp;
	// Healing Value
	int value = 5;
	public OBJ_HP_Potion(GamePanel gp) {

		super(gp);
		type = type_consumable;
		name = "Healing Potion";
		down1 = setup("/objects/hp_potion");
		description = "[" + name + "]\nRestores lost health by " + value + " hp.\n";
	}

	// Handles actions involved after consuming item.
	public void use(Entity entity){
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Consumed " + name + "!\n Restored 5 HP";
		entity.life += value;
		//gp.playSE(1); //sound effect for consuming potion
		if(gp.player.life > gp.player.maxlife){
			gp.player.life = gp.player.maxlife;
		}
	}
}