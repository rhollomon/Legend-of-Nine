package edu.nmsu.cs.legendofnine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    // DEBUG
    boolean checkDrawTime = false;

    
    

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

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
        if(code == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if (checkDrawTime == true) {
                checkDrawTime = false;
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
