package com.example.a2dshooter.utils;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.gameEntities.Enemy;
import com.example.a2dshooter.gameEntities.Entity;
import com.example.a2dshooter.gameEntities.Player;
import com.example.a2dshooter.gamePanels.HealthBar;
import com.example.a2dshooter.gamePanels.Joystick;
import com.example.a2dshooter.graphics.Animate;

import java.util.ArrayList;

public class DefaultValues {

    private Player player;
    private ArrayList<Enemy> enemies;

    //player
    public Paint playerPaint;
    public Paint healthPaint;
    public Paint borderPaint;
    public Entity healthBarEntity;
    public GameCamera healthCamera;

    public Joystick joystickMove;
    public Joystick joystickShoot;
    public Animate playerAnimator;
    public Context playerContext;

    //bullet
    public Context bulletContext;
    public Paint bulletPaint;

    //enemy
    public GameCamera enemyCamera;
    public ArrayList<HealthBar> enemyHealthBar = new ArrayList<>();
    public DisplayMetrics enemyDisplayMetrics;
    public Paint enemyPaint;
    public Context enemyContext;

    public DefaultValues(Player player, ArrayList<Enemy> enemies){
        update(player, enemies);
    }

    public void update(Player player, ArrayList<Enemy> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.playerPaint = player.paint;
        this.playerContext = player.context;

        this.healthPaint = player.healthbar.healthPaint;
        this.borderPaint = player.healthbar.borderPaint;
        this.healthBarEntity = player;
        this.healthCamera = player.healthbar.gameCamera;

        this.joystickMove = player.joystickMovement;
        this.joystickShoot = player.joystickShoot;
        this.playerAnimator = player.animator;

        if(!player.listOfBullets.isEmpty()){
            this.bulletContext = player.listOfBullets.get(0).context;
            this.bulletPaint = player.listOfBullets.get(0).paint;
        }

        //Log.d(TAG, "update: enemies.size: " + enemies.size());

        enemyHealthBar = new ArrayList<>();

        for(int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            enemyHealthBar.add(enemy.healthbar);
        }

        //Log.d(TAG, "update: healthbar.size: " + enemyHealthBar.size());

        if(!enemies.isEmpty()){
            this.enemyDisplayMetrics = enemies.get(0).displayMetrics;
            this.enemyPaint = enemies.get(0).paint;
            this.enemyContext = enemies.get(0).context;
            enemyCamera = enemies.get(0).gameCamera;
        }
    }
}
