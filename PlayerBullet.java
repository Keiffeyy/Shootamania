import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PlayerBullet here.
 * 
 * @author Keith Wong
 * @version 1.00
 */
public class PlayerBullet extends Projectile
{
    private boolean inWorld = false;
    private int yCoordinate;
    private int moveSpeed = -6;
    /**
     * Moves, checks it it's at the edge of the world, removes it if true
     * 
     */
    public void act() 
    {
        moveProjectile(moveSpeed); //moves the bullet at the given speed
        yCoordinate = getY();
        if (isAtWorldEdge(yCoordinate) == true)
            getWorld().removeObject(this);
        inWorld = inWorld(yCoordinate);
        if (hitOther(inWorld, Enemy.class) == true)
            getWorld().removeObject(this);
    }    
}
