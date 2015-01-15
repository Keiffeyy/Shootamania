import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Font;

/**
 * Counter that displays a number.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Instructions extends Actor
{
    public Instructions()
    {
        setImage(new GreenfootImage(500, 24));
        GreenfootImage image = getImage();
        Font font = image.getFont();
        image.setFont(font.deriveFont(24.0F));
        updateImage();
    }

    public void act() {
        if (Greenfoot.isKeyDown("z"))
            getWorld().removeObject(this);
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString("Arrow Keys to Move, z to Shoot", 1, 18);
    }
}
