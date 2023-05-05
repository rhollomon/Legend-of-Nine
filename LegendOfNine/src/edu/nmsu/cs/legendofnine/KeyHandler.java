package edu.nmsu.cs.legendofnine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // DEBUG
    boolean checkDrawTime = false;

    public KeyHandler (GamePanel gp) {
    	this.gp = gp;
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Title State
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        // Play State
        else if(gp.gameState == gp.playState) {
            playState(code);
        }
        // Pause State
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        // Dialouge State
        else if(gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        // Character State
        else if(gp.gameState == gp.characterState) {
            characterState(code);
        }
        // // Options State
        // else if(gp.gameState == gp.optionsState) {
        //     optionsState(code);
        // }
        // Game Over State
        else if(gp.gameState == gp.gameOverState) {
            gameOverState(code); 
        }
        // Trade State
        else if(gp.gameState == gp.tradeState) {
            tradeState(code); 
        }

    }

    public void titleState(int code) {
        
        if(code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum=1;
            }
        }
        if(code == KeyEvent.VK_S) {
            gp.ui.commandNum++;

            if (gp.ui.commandNum>1)
            {
                gp.ui.commandNum=0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                // NEW GAME
                gp.gameState = gp.playState;
            }
            if (gp.ui.commandNum==1){
                // QUIT
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        
        if(code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if (checkDrawTime == true) {
                checkDrawTime = false;
            }
        }
    }

    public void pauseState(int code) {
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }

        playerInventory(code);

    }
    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(8); 
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(8);  
        }
        if (code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if(gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    public void tradeState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
        }
        
        if (gp.ui.subState == 1) {

            // We want to choose items in npc inventory
            npcInventory(code);

            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState=0;
            }

        }
        if (gp.ui.subState == 2) {

            // We want to choose items in PLAYER inventory
            playerInventory(code);

            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState=0;
            }

        }

    }

    public void playerInventory(int code) {
            //Inventory
        if(code == KeyEvent.VK_W){
            if(gp.ui.playerSlotRow != 0){
                gp.ui.playerSlotRow--;
                gp.playSE(8);       
                            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.playerSlotCol != 0){
                gp.ui.playerSlotCol--;
                gp.playSE(8); 
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.playerSlotRow != 3){
                gp.ui.playerSlotRow++;
                gp.playSE(8); 
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.playerSlotCol != 4){
                gp.ui.playerSlotCol++;
                gp.playSE(8); 
            }
        }
    }
    
    public void npcInventory(int code) {
        //Inventory
    if(code == KeyEvent.VK_W){
        if(gp.ui.npcSlotRow != 0){
            gp.ui.npcSlotRow--;
            gp.playSE(8);       
                        }
    }
    if(code == KeyEvent.VK_A){
        if(gp.ui.npcSlotCol != 0){
            gp.ui.npcSlotCol--;
            gp.playSE(8); 
        }
    }
    if(code == KeyEvent.VK_S){
        if(gp.ui.npcSlotRow != 3){
            gp.ui.npcSlotRow++;
            gp.playSE(8); 
        }
    }
    if(code == KeyEvent.VK_D){
        if(gp.ui.npcSlotCol != 4){
            gp.ui.npcSlotCol++;
            gp.playSE(8); 
        }
    }
}

    @Override
    public void keyReleased(KeyEvent e) {
            
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        

    }
    
}
