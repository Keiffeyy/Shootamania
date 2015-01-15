import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Font;

/**
 * Counter that displays a number.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Counter extends Actor
{
    private int score = 0;
    private int stringLength;
    private static int highScore = 0;
    private boolean showHighScore;

    public Counter(boolean whichScore)
    {
        showHighScore = whichScore; //displays high score if true, current score if false
        stringLength = 1000;
        setImage(new GreenfootImage(stringLength, 24));
        GreenfootImage image = getImage();
        Font font = image.getFont();
        image.setFont(font.deriveFont(24.0F)); // sets font of size 24
        updateImage();
    }

    public void act() {
        score = Player.getScore();
        updateImage();
        if (score > highScore)
            highScore = score;
    }

    public int getScore()
    {
        return score;
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        if (showHighScore == false)
            image.drawString("Score: " + score, 1, 18);
        else
            image.drawString("High Score: " + highScore, 1, 18);
    }
}
