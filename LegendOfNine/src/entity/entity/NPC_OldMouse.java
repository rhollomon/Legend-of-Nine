package entity;
import edu.nmsu.cs.legendofnine.GamePanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import edu.nmsu.UtilityTool;
import javax.imageio.ImageIO;

public class NPC_OldMouse extends Entity{
    
    public NPC_OldMouse(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }
    /**
	 * Sends player sprites to entity variables
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

	} // end getPlayerImage
	
    /**
	 * A.I. Functionality for the OldMouse
	 * Overrides the method within the Entity.java file
	 * 
	 */
    public void setAction(){

        actionLockCounter ++;

        // Direction will not change for the next 120 frames(about 2 seconds)
        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100) +1; // Random Number 1 - 100

            //25% chance to go up, down, left, or right
            if(i <= 25){
                direction = "up";
            }else if(i > 25 && i <= 50){
                direction = "down";
            }else if(i > 50 && i <= 75 ){
                direction = "left";
            }else if(i > 75 && i <= 100 ){
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
	
	

    

}