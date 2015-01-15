import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;
import java.util.*;
/**
 * Characteristics and actions the enemy can perform
 * 
 * @author Keith Wong
 * @version 1.00
 */
public class Enemy extends Character{
    private boolean moveRight = true; //checks to see if the character should be moving right or left
    private int points = 500; //points given to player upon death
    private boolean moveForward = true; //checks to see if the character should be moving up or down
    private int moveForwardBy; //randomized at object creation between 300 and 500, sees how far the enemy plane will move forward
    private int horizontalSpeed; //randomizzed at object creation between 4 and 7, sees how qucikly the enemy plane will move horizontaly
    private int verticalSpeed; //randomized at object creation between 3 and 5, sees how quickly the enemy plane will move up and down
    private int shootSpeed = 30; //bullet lag, lower numbers will make the plane shoot faster, and vice versa
    private int health = 3;
    private int timer = 0;
    GreenfootSound dead = new GreenfootSound("EnemyDead.wav");
    public Enemy(){
        moveForwardBy = Greenfoot.getRandomNumber(100) + 200;
        horizontalSpeed = Greenfoot.getRandomNumber(4) + 3;
        verticalSpeed = Greenfoot.getRandomNumber(3) + 2;
        int sprite = Greenfoot.getRandomNumber(3);
        if (sprite == 0)
            setImage("EnemyPlane1.png");
        else if (sprite == 1)
            setImage("EnemyPlane2.png");
        else
            setImage("EnemyPlane3.png");
    }

    /**
     * Moving, shooting, dying
     * 
     */
    public void act() 
    {
        timer();
        if (moveRight == true)
            moveHorizontal(getX(), getY(), horizontalSpeed);
        if (moveRight == false)
            moveHorizontal (getX(), getY(), horizontalSpeed*-1);
        if (moveForward == true)
            moveVertical (getX(), getY(), verticalSpeed);
        if (moveForward == false)
            moveVertical (getX(), getY(), verticalSpeed*-1);
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
     * Shoots a bullet with a speed given from enemy's location
     */
    private void shootBullet (int x, int y){
        getWorld().addObject(new EnemyBullet(verticalSpeed + 2), x, y);
    }

    /**
     * 
     * Drops a power up at enemy's location
     */
    private void dropPowerUp(){
        getWorld().addObject(new PowerUp(), getX(),getY());
    }

    /**
     * 
     * Keeps enemy within certain boundaries, boundaries are (10 - 390, 20 - moveForwardBy)  
     */
    private void boundaries(){
        if (getX() >= 390)
            moveRight = false;
        if (getX() <= 10)
            moveRight = true;
        if (getY() >= moveForwardBy)
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
     * Adds to the timer upon each Greenfoot frame
     */
    private void timer(){
        timer++;
    }
}