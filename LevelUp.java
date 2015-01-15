import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelUp extends Actor
{
    int timer = 0;
    boolean playSound = true;
    GreenfootSound leveling = new GreenfootSound("LevelUp.wav");
    /**
     * 
     * Appears on the screen for 40 Greenfoot frames, plays the level up sound once, then disappears
     */
    public void act() 
    {
        timer++;
        if (playSound == true)
            playSound();
        if(timer >= 40)
            getWorld().removeObject(this);
    }

    /**
     * 
     * Plays the sound then marks the sound as played
     */
    private void playSound(){
        leveling.play();
        playSound = false;
    }
}
