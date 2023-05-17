package com.example.a2dshooter.GameStateManagement;

import com.example.a2dshooter.gameEntities.Bullet;
import com.example.a2dshooter.gameEntities.Enemy;
import com.example.a2dshooter.gameEntities.Player;
import com.example.a2dshooter.gameEntities.PlayerState;
import com.example.a2dshooter.utils.DefaultValues;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * GameState - used when Saving the current game state
 * to a file. Stores all Saved fields and initializes
 * DefaultValues data class with all transient fields.
 */

public class GameState implements Serializable {

    public Player player;
    public ArrayList<Enemy> enemies;
    public transient DefaultValues defaultValues;

    public GameState(Player player, ArrayList<Enemy> enemies) {
        this.defaultValues = new DefaultValues(player, enemies);
        this.player = player;
        this.enemies = enemies;
    }

    public void update(Player player, ArrayList<Enemy> enemies) {
        this.defaultValues.update(player, enemies);
        this.player = player;
        this.enemies = enemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public void update(GameState newGameState) {
        this.player = newGameState.player;
        this.enemies = newGameState.enemies;
        this.player.paint = defaultValues.playerPaint;
        this.player.context = defaultValues.playerContext;

        this.player.healthbar.entity = this.player;
        this.player.healthbar.healthPaint = defaultValues.healthPaint;
        this.player.healthbar.borderPaint = defaultValues.borderPaint;
        this.player.healthbar.gameCamera = defaultValues.healthCamera;

        this.player.joystickMovement = defaultValues.joystickMove;
        this.player.joystickShoot = defaultValues.joystickShoot;
        this.player.animator = defaultValues.playerAnimator;
        this.player.playerState = new PlayerState(player);

        for (Bullet bullet : player.listOfBullets) {
            bullet.context = defaultValues.bulletContext;
            bullet.paint = defaultValues.bulletPaint;
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemy.gameCamera = defaultValues.enemyCamera;

            enemy.healthbar.borderPaint = defaultValues.borderPaint;
            enemy.healthbar.healthPaint = defaultValues.healthPaint;
            enemy.healthbar.entity = enemy;
            enemy.healthbar.gameCamera = enemy.gameCamera;

            enemy.displayMetrics = defaultValues.enemyDisplayMetrics;
            enemy.player = player;
            enemy.listOfMines = new ArrayList<>();
            enemy.paint = defaultValues.enemyPaint;
            enemy.context = defaultValues.enemyContext;
        }
    }

}
