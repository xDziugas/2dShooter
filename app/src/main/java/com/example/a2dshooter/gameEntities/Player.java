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
import com.example.a2dshooter.gamePanels.XpBar;
import com.example.a2dshooter.utils.Util;

import java.util.ArrayList;

public class Player extends Entity{

    private int healthPoints;
    private final HealthBar healthbar;
    private boolean ableToShoot = false;
    private final Joystick joystickMovement;
    private final Joystick joystickShoot;
    private int fireRate = 60; //bullet every X frames
    private int level = 0;

    public final ArrayList<Bullet> listOfBullets = new ArrayList<>();

    public Player(Context context, Joystick joystickMovement, Joystick joystickShoot, DisplayMetrics displayMetrics, int color, GameCamera gameCamera){
        super(
                context,
                ContextCompat.getColor(context, R.color.player),
                displayMetrics.widthPixels / 2.0,
                displayMetrics.heightPixels / 2.0,
                PLAYER_RADIUS
        );

        this.healthbar = new HealthBar(context, this, MAX_HEALTH_POINTS_PLAYER, gameCamera);
        setHealthPoints(MAX_HEALTH_POINTS_PLAYER);

        Paint paint = new Paint();
        paint.setColor(color);

        this.joystickMovement = joystickMovement;
        this.joystickShoot = joystickShoot;
    }

    public void draw(Canvas canvas, GameCamera gameCamera){
        super.draw(canvas, gameCamera);
        healthbar.draw(canvas, this.getHealthPoints());
    }

    public void shoot(){
        listOfBullets.add(new Bullet(context, this, 1, MAX_BULLET_DAMAGE));
    }

    public void moveBullets(){
        for(Bullet bullet : listOfBullets){
            bullet.move();
        }
    }

    public void drawBullets(Canvas canvas, GameCamera gameCamera){
        for(Bullet bullet : listOfBullets){
            bullet.draw(canvas, gameCamera);
        }
    }

    public ArrayList<Bullet> getBullets(){
        return this.listOfBullets;
    }

    @Override
    public void move(){
        velocityX = joystickMovement.getActuatorX() * MAX_SPEED_PER_FRAME_PLAYER;
        velocityY = joystickMovement.getActuatorY() * MAX_SPEED_PER_FRAME_PLAYER;

        double facingX = joystickShoot.getActuatorX() * MAX_SPEED_PER_FRAME_PLAYER;
        double facingY = joystickShoot.getActuatorY() * MAX_SPEED_PER_FRAME_PLAYER;

        //update position
        super.move();

        //update direction
        if(facingX != 0 || facingY != 0){
            //unit vector
            double distance = Util.getDistanceBetweenPoints(0, 0, facingX, facingY);
            directionX = facingX / distance;
            directionY = facingY / distance;
        }
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints){
        this.healthPoints = Math.max(healthPoints, 0);
    }

    public boolean canShoot(){
        return this.ableToShoot;
    }

    public void setCanShoot(boolean ableToShoot){
        this.ableToShoot = ableToShoot;
    }

    public int getLevel() {
        return level;
    }

    public void incLevel() {
        this.level++;
        fireRate *= 0.8;
    }

    public int getFireRate(){
        return fireRate;
    }


}
