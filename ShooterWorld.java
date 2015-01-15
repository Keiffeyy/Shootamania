import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * The world where this game is played in.
 * 
 * @author Keith Wong
 * @version 1.00
 */
public class ShooterWorld extends World
{
    private static int numEnemies;
    private boolean spawnBoss = false;
    /**
     * Creates world and places player, enemy, background, score counters, and instructions on the screen
     * 
     */
    public ShooterWorld()
    {    
        // Create a new world with 400x600 cells with a cell size of 1x1 pixels.
        super(400, 600, 1, false);
        addObject (new Player(), 200, 550);
        addObject (new Enemy(),200, -100);
//         addObject(new Background(),200,0);
        addObject (new Counter(false), 510, 550);
        addObject (new Counter(true), 510, 580);
        addObject (new Instructions(), 280, 200);
        setPaintOrder(ScoreBoard.class, Instructions.class, Counter.class, LevelUp.class, Explosion.class, Player.class, Boss.class, Enemy.class,
            PlayerBullet.class, EnemyBullet.class, PowerUp.class, Background.class);
    }

    /**
     * 
     * Will Spawn another enemy once there are none left on screen
     */
    public void act(){
        checkNumEnemies(); //Spawns enemies if there are none left on screen
        spawnBoss();//Spawns at 20 kills
    }

    /**
     * 
     * Checks if there are enemies on screen, if not, another one will spawn
     */
    private void checkNumEnemies(){
        List listEnemies = getObjects(Enemy.class);
        numEnemies = listEnemies.size();
        if (numEnemies == 0){
            addObject(new Enemy(), Greenfoot.getRandomNumber(100) + 300, -100);
            if (Player.getKills() > 20)
                addObject(new Enemy(), Greenfoot.getRandomNumber(100) + 300, -100);
            if (Player.getKills() > 50)
                addObject(new Enemy(), Greenfoot.getRandomNumber(100) + 300, -100);
            if (Player.getKills() > 100)
                addObject(new Enemy(), Greenfoot.getRandomNumber(100) + 300, -100);
        }

    }

    /**
     * 
     * Checks if 20 enemies have been killed, spawns boss if true
     */
    private void spawnBoss(){
        if (Player.getKills()%20 == 0 && spawnBoss == true){
            addObject(new Boss(), 200, -200);
            spawnBoss = false;
        }
        if (Player.getKills()%20 != 0)
            spawnBoss = true;
    }
}