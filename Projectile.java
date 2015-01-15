import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * WMakes projectiles move and dissappear at the edge of the screen or upon collision with respective object
 * 
 * @author Keith Wong
 * @version 1.00
 */
public class Projectile extends Actor
{
    /**
     * 
     * Returns true if it hits another object, false if not
     */
    boolean hitOther(boolean inWorld, Class clss){
        if (inWorld == true){
            Actor actor = getOneObjectAtOffset(0, 0, clss);
            if(actor != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * Moves projectiles a given amount
     */
    void moveProjectile(int amount){
        setLocation(getX(), getY() + amount);
    }

    /**
     * 
     * Returns true if the projectile is within the boundaries of the world
     */
    public boolean inWorld(int yCoordinate){
        if (yCoordinate > 1 && yCoordinate < 598)
            return true;
        else
            return false;
    }

    /**
     * 
     * Returns true if the projectile is at the edge of the world
     */
    public boolean isAtWorldEdge(int yCoordinate){
        if (yCoordinate >=599 || yCoordinate == 0)
            return true;
        else
            return false;
    }
}