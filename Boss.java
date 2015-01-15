import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;
import java.util.*;
/**
 * Characteristics and actions of the boss.
 * 
 * @author Keith Wong 
 * @version 1.00
 */
public class Boss extends Character
{
    private boolean moveRight = true; //moves right when true, left when false
    private int points = 15000; //Points given to player upon defeat
    private boolean moveForward = true; //moves forward if true, back in false
    private int health = 50; //Amount of hits needed to defeat boss
    private int shootSpeed = 20; //Speed of shooting, lower numbers make the boss shoot faster
    private int timer = 0;
    GreenfootSound dead = new GreenfootSound("BossDead.wav");
    /**
     * Moving, Shooting, dying
     * 
     */
    public void act() 
    {
        timer();
        if (moveRight == true)
            moveHorizontal(getX(), getY(), 5);
        if (moveRight == false)
            moveHorizontal (getX(), getY(), -5);
        if (moveForward == true)
            moveVertical (getX(), getY(), 3);
        if (moveForward == false)
            moveVertical (getX(), getY(), -3);
        boundaries();
        if (enemyShootBullet(timer,shootSpeed) == true)
            shootBullet(getX(),getY() + 5);
        if (hitByObject(PlayerBullet.class) == true){
            health--;
            if (health == 0){
                die();
            }
        }    
    }

    /**
     * 
     * Creates a bullet object at the location of the boss
     */
    private void shootBullet (int x, int y){
        getWorld().addObject(new EnemyBullet(6), x, y);
    }

    /**
     * 
     * Increases the timer by 1 upon each Greenfoot frame
     */
    private void timer(){
        timer++;
    }

    /**
     * 
     * Keeps enemy within certain boundaries, boundaries are (20 - 380, 20 - 200)
     */
    private void boundaries(){
        if (getX() >= 380)
            moveRight = false;
        if (getX() <= 20)
            moveRight = true;
        if (getY() >= 200)
            moveForward = false;
        if (getY() <= 20)
            moveForward = true;
    }

    /**
     * 
     * Actions to be performed upon death
     */
    private void die(){
        dropPowerUp();
        Player.addScore(points);
        Player.addKills();
        dead.play();
        getWorld().addObject(new Explosion(), getX(), getY());
        getWorld().removeObject(this);
    }

    /**
     * 
     * Creates a PowerUp object at the boss' location
     */
    private void dropPowerUp(){
        getWorld().addObject(new PowerUp(), getX(),getY());
    }
}
