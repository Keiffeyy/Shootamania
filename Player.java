import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CHaracteristics and actions the player can perform
 * 
 * @author Keith Wong
 * @version 1.00
 */
public class Player extends Character {
    private static int powerUps; //Number of power ups collected
    private int powerUpLevel; //Checks the level of power ups, higher gives more boosts
    private int previousPowerLevel = 0;
    private static int score; //Score of player, increased when enemies are killed and power ups are picked up
    private boolean inWorld = true; //checks if character is still present in world
    private static int kills; //number of enemy kills the player has
    private static int shootSpeed = 60; //Bullet lag, lower numbers shoot faster
    private static boolean bossDefeated;
    GreenfootSound shooting = new GreenfootSound("PlayerShoot.wav");
    GreenfootSound death = new GreenfootSound("PlayerDead.wav");
    public Player(){
        bossDefeated = false;
        powerUps = 0;
        score = 0;
        kills = 0;
    }

    /**
     *
     * Move, shoot, check if hit 
     */
    public void act() {
        levelUp();
        if (Greenfoot.isKeyDown("left"))
            moveHorizontal(getX(), getY(), -6);
        if (Greenfoot.isKeyDown("right"))
            moveHorizontal(getX(), getY(), 6);
        if (Greenfoot.isKeyDown("up"))
            moveVertical(getX(), getY(), -6);
        if (Greenfoot.isKeyDown("down"))
            moveVertical(getX(), getY(), 6);
        boundaries();
        if (Greenfoot.isKeyDown("z") == true){
            if (isBulletInRange(shootSpeed, PlayerBullet.class) == true){
                powerUps();
            }
        }
        if (inWorld == true)
            isHit(Enemy.class);
        if (inWorld == true)
            isHit(EnemyBullet.class);
    }

    /**
     * 
     * Checks if the player is at the edge of the screen, stops them from moving past the edge
     */
    private void boundaries(){
        if (getX() > 400)
            setLocation(400,getY());
        if (getX() < 0)
            setLocation (0, getY());
        if (getY() > 600)
            setLocation(getX(), 600);
        if (getY() < 0)
            setLocation (getX(), 0);
    }

    /**
     * 
     * Shoots a bullet at player location
     */
    private void shootBullet (int x, int y){
        getWorld().addObject(new PlayerBullet(), x, y);
    }

    /**
     * 
     * Changes power up level and powers gained upon increased power up level
     */
    private void powerUps(){
        if (powerUps > 50)
            powerUpLevel = 3;
        else if (powerUps > 20)
            powerUpLevel = 2;
        else if (powerUps > 5)
            powerUpLevel = 1;
        if (powerUpLevel == 0) 
            shootBullet(getX(), getY());//Shoot a bullet from the center of the plane
        if (powerUpLevel == 1){
            shootSpeed = 50; //Shoot faster
            shootBullet(getX(), getY()); //Shoot a bullet form the center of the plane
        }
        if (powerUpLevel == 2){
            shootBullet(getX() - 40, getY()); //Shoots a bullet to the left of the plane
            shootBullet(getX() + 40, getY()); //Shoots a bullet to the right of the plane
        }
        if (powerUpLevel == 3){
            shootSpeed = 60; //Shoot slower
            shootBullet(getX() - 40, getY()); //Shoots a bullet to the left of the plane
            shootBullet(getX(), getY()); //Shoots a bullet at the center of the plane
            shootBullet(getX() + 40, getY()); //Shoot at bullet form the right of the plane
        }
    }

    /**
     * 
     * Checks if the player had leveled up, displays LEVEL UP if true
     */
    private void levelUp(){
        if (previousPowerLevel != powerUpLevel)
            getWorld().addObject(new LevelUp(), getX(),getY());
        previousPowerLevel = powerUpLevel;
    }

    /**
     * 
     * Checks if player is hit by the enemy
     */
    private void isHit(Class clss){
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        if(actor != null) {
            inWorld = false;
            getWorld().addObject(new ScoreBoard(score), 200, 300);
            death.play();
            getWorld().addObject(new Explosion(), getX(), getY());
            getWorld().removeObject(this);
        }
    }

    /**
     * 
     * Adds power to powerUps attribute
     */
    public static void addPower(){
        powerUps++;
    }

    /**
     * 
     * Returns powerUps
     */
    public int getPowerUps(){
        return powerUps;
    }

    /**
     * 
     * Adds points to points attribute
     */
    public static void addScore(int points){
        score += points;
    }

    /**
     * 
     * Returns score
     */
    public static int getScore(){
        return score;
    }

    /**
     * 
     * Returns kills
     */
    public static int getKills(){
        return kills;
    }

    /**
     * 
     * Adds to kill counter, boss appears every 20 kills
     */
    public static void addKills(){
        kills++;
    }

    /**
     * 
     * Changes the variable of the boss being defeat to true
     */
    public static void bossDefeated(){
        bossDefeated = true;
    }

    /**
     * 
     * Returns if the boss has been defeated
     */
    public static boolean getBossDefeated(){
        return bossDefeated;
    }
}