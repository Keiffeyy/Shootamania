import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Parent class to all planes in the game, includes moving, hit detection, and bullet lag
 * 
 * @author Keith Wong 
 * @version 1.00
 */
public class Character extends Actor
{

    /**
     * 
     * Moves the character horizontally by the amount given
     */
    void moveHorizontal(int x, int y, int amount){
        setLocation(x + amount, y);
    }

    /**
     * 
     * Moves the character vertically by the amount given
     */
    void moveVertical(int x, int y, int amount){
        setLocation (x, y + amount);
    }

    /**
     * 
     * Returns true if the enemy is hit by bullet, false otherwise
     */
    boolean hitByObject(Class clss){
        Actor actor = getOneObjectAtOffset(35, 35, clss);
        if(actor != null) {
            return true;
        }
        return false;
    }

    /**
     * 
     * Returns true if a bullet is in range, not allowing the player to shoot another bullet
     */
    boolean isBulletInRange(int range, Class clss){
        List objectsInRange = getObjectsInRange(range, clss);
        if (objectsInRange.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * 
     * returns true if the plane has cooled down enough to be able to shoot, false otherwise
     */
    boolean enemyShootBullet(int timer, int shootSpeed){
        if (timer%shootSpeed == 0)
            return true;
        else
            return false;
    }
}