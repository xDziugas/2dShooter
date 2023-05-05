package com.example.a2dshooter.GameStateManagement;

import com.example.a2dshooter.gameEntities.Bullet;
import com.example.a2dshooter.gameEntities.Enemy;
import com.example.a2dshooter.gameEntities.Player;
import com.example.a2dshooter.gameEntities.PlayerState;
import com.example.a2dshooter.utils.DefaultValues;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    public Player player;
    public ArrayList<Enemy> enemies;
    public transient DefaultValues defaultValues;

    public GameState(Player player, ArrayList<Enemy> enemies) {
        this.defaultValues = new DefaultValues(player, enemies);
        this.player = player;
        this.enemies = enemies;
    }

    public void update(Player player, ArrayList<Enemy> enemies){
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
        this.player.healthbar = defaultValues.playerHealthBar;
        this.player.healthbar.entity = this.player;
        this.player.joystickMovement = defaultValues.joystickMove;
        this.player.joystickShoot = defaultValues.joystickShoot;
        this.player.animator = defaultValues.playerAnimator;
        this.player.playerState = new PlayerState(player);

        for(Bullet bullet : player.listOfBullets){
            bullet.context = defaultValues.bulletContext;
            bullet.paint = defaultValues.bulletPaint;
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            if(i <= defaultValues.enemyCamera.size() - 1){ //?
                enemy.gameCamera = defaultValues.enemyCamera.get(i);
                enemy.healthbar = defaultValues.enemyHealthBar.get(i);
            }

            enemy.healthbar.entity = enemy;
            enemy.displayMetrics = defaultValues.enemyDisplayMetrics;
            enemy.player = player;
            enemy.listOfMines = new ArrayList<>();
            enemy.paint = defaultValues.enemyPaint;
            enemy.context = defaultValues.enemyContext;
        }
    }

}
