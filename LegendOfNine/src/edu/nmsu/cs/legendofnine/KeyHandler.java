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

        // Title State - Move cursor 
        if (gp.gameState == gp.titleState) {

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
        // Play State
        else if(gp.gameState == gp.playState){
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
        } // end of if

        //Pause State
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        //Dialogue State
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
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
