package com.example.a2dshooter.gameEntities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import androidx.core.content.ContextCompat;

import com.example.a2dshooter.Game;
import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.R;
import com.example.a2dshooter.gamePanels.HealthBar;

import java.util.ArrayList;

public class Enemy extends Entity {

    private static final double framesUntilSpawnMax = 60 / (SPAWNS_PER_MINUTE / 60.0);
    private static double framesUntilSpawn = framesUntilSpawnMax;
    private final GameCamera gameCamera;
    private int healthPoints = MAX_HEALTH_POINTS_ENEMY;
    private final HealthBar healthbar;
    private final Player player;
    private Boolean isAlive = true;
    private final ArrayList<Bullet> listOfMines = new ArrayList<>();
    private final DisplayMetrics displayMetrics;
    private final int xpOnKill = XP_ON_KILL_ENEMY;

    public Enemy(Context context, Player player, DisplayMetrics displayMetrics, GameCamera gameCamera){
        super(
                context,
                ContextCompat.getColor(context, R.color.enemy),
                ENEMY_RADIUS
        );

        this.player = player;
        this.gameCamera = gameCamera;
        this.displayMetrics = displayMetrics;

        positionX = getSpawnPositionX();
        positionY = getSpawnPositionY(positionX);

        positionX += player.getPositionX();
        positionY += player.getPositionY();

        setHealthPoints(MAX_HEALTH_POINTS_ENEMY);
        this.healthbar = new HealthBar(context, this, MAX_HEALTH_POINTS_ENEMY, gameCamera);
    }

    public void draw(Canvas canvas, GameCamera gameCamera){
        super.draw(canvas, gameCamera);
        healthbar.draw(canvas, this.getHealthPoints());
    }

    @Override
    public void move() {

        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        //calc distance
        double distanceToPlayer = EntityGeneral.getDistanceBetweenObjects(this, player);

        //calc direction
        double directionX = distanceToPlayerX / distanceToPlayer;
        double directionY = distanceToPlayerY / distanceToPlayer;

        //set velocity
        if(distanceToPlayer > 0){
            velocityX = directionX * MAX_SPEED_PER_FRAME_ENEMY;
            velocityY = directionY * MAX_SPEED_PER_FRAME_ENEMY;
        }else{
            velocityX = 0;
            velocityY = 0;
        }

        //update the position of the enemy
        super.move();

        if(EntityGeneral.isColliding(this, player)){
            setAlive(false);
        }
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public static boolean readyToSpawn() {
        if(framesUntilSpawn <= 0){
            framesUntilSpawn += framesUntilSpawnMax;
            return true;
        } else{
            framesUntilSpawn--;
            return false;
        }
    }

    public void placeMine(){
        listOfMines.add(new Bullet(context, this, 0));
    }

    public void drawMines(Canvas canvas, GameCamera gameCamera){
        for(Bullet bullet : listOfMines){
            bullet.draw(canvas, gameCamera);
        }
    }

    public int getXpOnKill(){
        return this.xpOnKill;
    }

    public double getSpawnPositionX(){
        return (Math.random() * 2 - 1) * ((double) (displayMetrics.widthPixels / 2) + ENEMY_SPAWN_POSITION_OFFSET);
    }

    public double getSpawnPositionY(double positionX){
        double radiusSquared = ((double) displayMetrics.widthPixels + ENEMY_SPAWN_POSITION_OFFSET) * ((double) displayMetrics.widthPixels + ENEMY_SPAWN_POSITION_OFFSET) / 4;
        double xSquared = positionX * positionX;

        double plusMinusOne = (Math.round(Math.random()) * 2 - 1);

        return Math.sqrt(radiusSquared - xSquared) * plusMinusOne;
    }

    public ArrayList<Bullet> getMines(){
        return this.listOfMines;
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints){
        this.healthPoints = Math.max(0, healthPoints);
    }
}
