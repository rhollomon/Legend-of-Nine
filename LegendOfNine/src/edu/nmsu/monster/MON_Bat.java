package edu.nmsu.monster;

import java.util.Random;

import edu.nmsu.cs.legendofnine.GamePanel;
import entity.Entity;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Bat";
        speed = 1;
        maxlife = 3;
        life = maxlife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("/monster/bat_down_1");
        up2 = setup("/monster/bat_down_2");
        down1 = setup("/monster/bat_down_1");
        down2 = setup("/monster/bat_down_2");
        left1 = setup("/monster/bat_down_1");
        left2 = setup("/monster/bat_down_2");
        right1 = setup("/monster/bat_down_1");
        right2 = setup("/monster/bat_down_2");
    }
    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i<=25) {
                direction = "up";
            }

            if(i > 25 && i <= 50) {
                direction = "down";
            }

            if(i > 50 && i <= 75) {
                direction = "left";
            }

            if(i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
    }

}
