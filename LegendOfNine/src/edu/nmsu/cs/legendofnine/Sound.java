package edu.nmsu.cs.legendofnine;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/LON_OW_theme.wav");
        soundURL[1] = getClass().getResource("/sound/dealdmg.wav");
        soundURL[2] = getClass().getResource("/sound/gameover.wav");
        soundURL[3] = getClass().getResource("/sound/lvlup.wav");
        soundURL[4] = getClass().getResource("/sound/opendoor.wav");
        soundURL[5] = getClass().getResource("/sound/powerup.wav");
        soundURL[6] = getClass().getResource("/sound/takedmg.wav");
        soundURL[7] = getClass().getResource("/sound/unlock.wav");
        soundURL[8] = getClass().getResource("/sound/cursor.wav");

        //To be implemented, sound effect for moving cursor while in inventory
        //soundURL[1];
    }

    public void setFile(int i) {
        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e) {

        }
    }
    public void play() {

        clip.start();
    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {

        clip.stop();
    }
}
