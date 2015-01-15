import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Moving and collection of power up by player
 * 
 * @author Keith Wong
 * @version 1.00
 */
public class PowerUp extends Projectile
{ 
    private boolean inWorld = false;
    private int yCoordinate;
    private int moveSpeed = 3;
    GreenfootSound pickup = new GreenfootSound("item.wav");
    /**
     * Moves, checks if collected by player, checks if hit boundaries of world
     * 
     */
    public void act() 
    {
        moveProjectile(moveSpeed); //Moves the PowerUp at the given speed
        yCoordinate = getY();
        if (isAtWorldEdge(yCoordinate) == true)
            getWorld().removeObject(this);
        inWorld = inWorld(yCoordinate);
        if (hitOther(inWorld, Player.class) == true){
            Player.addPower();
            Player.addScore(200);
            pickup.play();
            getWorld().removeObject(this);
        }
    }    
}
