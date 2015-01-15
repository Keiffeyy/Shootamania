import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullets that the enemy shoots
 * 
 * @author Keith Wong
 * @version 1.00
 */
public class EnemyBullet extends Projectile
{
    private boolean inWorld = false;
    private int yCoordinate;
    private int moveSpeed; //Move speed randomized by enemy class upon creation
    public EnemyBullet(int s){
        moveSpeed = s;
    }

    /**
     * Moves, checks it it's at the edge of the world, removes it if true
     * 
     */
    public void act() 
    {
        moveProjectile(moveSpeed);
        yCoordinate = getY();
        if (isAtWorldEdge(yCoordinate) == true)
            getWorld().removeObject(this);
        inWorld = inWorld(yCoordinate);
        if (hitOther(inWorld, ScoreBoard.class) == true)
            getWorld().removeObject(this);
    }    
}
