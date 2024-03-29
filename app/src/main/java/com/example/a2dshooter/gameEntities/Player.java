package com.example.a2dshooter.gameEntities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import androidx.core.content.ContextCompat;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.R;
import com.example.a2dshooter.gamePanels.HealthBar;
import com.example.a2dshooter.gamePanels.Joystick;
import com.example.a2dshooter.graphics.Animate;
import com.example.a2dshooter.utils.Constants;
import com.example.a2dshooter.utils.Util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player - main Entity, controlled by a player using Joysticks.
 */

public class Player extends Entity implements Serializable {

    private int healthPoints;
    public HealthBar healthbar;
    private boolean ableToShoot = false;
    public transient Joystick joystickMovement;
    public transient Joystick joystickShoot;
    private int fireRate = 60; //bullet every X frames
    private int level = 0;

    public ArrayList<Bullet> listOfBullets = new ArrayList<>();
    public transient PlayerState playerState;
    public transient Animate animator;

    public Player(Context context, Joystick joystickMovement, Joystick joystickShoot, DisplayMetrics displayMetrics, int color, GameCamera gameCamera, Animate animator) {
        super(
                context,
                ContextCompat.getColor(context, R.color.player),
                displayMetrics.widthPixels / 2.0,
                displayMetrics.heightPixels / 2.0,
                Constants.PLAYER_RADIUS
        );

        this.healthbar = new HealthBar(context, this, Constants.MAX_HEALTH_POINTS_PLAYER, gameCamera);
        setHealthPoints(Constants.MAX_HEALTH_POINTS_PLAYER);

        Paint paint = new Paint();
        paint.setColor(color);

        this.joystickMovement = joystickMovement;
        this.joystickShoot = joystickShoot;

        this.playerState = new PlayerState(this);
        this.animator = animator;
    }

    public void draw(Canvas canvas, GameCamera gameCamera) {
        //super.draw(canvas, gameCamera);
        healthbar.draw(canvas, this.getHealthPoints());
        animator.draw(canvas, gameCamera, this);
    }

    public void shoot() {
        listOfBullets.add(new Bullet(context, this, 1, Constants.MAX_BULLET_DAMAGE));
    }

    public void moveBullets() {
        for (Bullet bullet : listOfBullets) {
            bullet.move();
        }
    }

    public void drawBullets(Canvas canvas, GameCamera gameCamera) {
        for (Bullet bullet : listOfBullets) {
            bullet.draw(canvas, gameCamera);
        }
    }

    public ArrayList<Bullet> getBullets() {
        return this.listOfBullets;
    }

    @Override
    public void move() {
        velocityX = joystickMovement.getActuatorX() * Constants.MAX_SPEED_PER_FRAME_PLAYER;
        velocityY = joystickMovement.getActuatorY() * Constants.MAX_SPEED_PER_FRAME_PLAYER;

        double facingX = joystickShoot.getActuatorX() * Constants.MAX_SPEED_PER_FRAME_PLAYER;
        double facingY = joystickShoot.getActuatorY() * Constants.MAX_SPEED_PER_FRAME_PLAYER;

        //update position
        super.move();

        //update direction
        if (facingX != 0 || facingY != 0) {
            double distance = Util.getDistanceBetweenPoints(0, 0, facingX, facingY);
            directionX = facingX / distance;
            directionY = facingY / distance;
        }

        playerState.update();
    }

    public void incLevel() {
        this.level++;
        fireRate *= 0.8;
    }

    public void updateFireRate(double multiplier) {
        fireRate /= (multiplier);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = Math.max(healthPoints, 0);
    }

    public boolean canShoot() {
        return this.ableToShoot;
    }

    public void setCanShoot(boolean ableToShoot) {
        this.ableToShoot = ableToShoot;
    }

    public int getLevel() {
        return level;
    }

    public int getFireRate() {
        return fireRate;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public HealthBar getHealthbar() {
        return healthbar;
    }
}
