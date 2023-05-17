package com.example.a2dshooter.gameEntities;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.R;
import com.example.a2dshooter.utils.Constants;

import java.io.Serializable;

/**
 * Main Entity used for killing other entities. Player/Enemy contains Bullet.
 */

public class Bullet extends Entity implements Serializable {

    private int damage;

    public Bullet(Context context, Entity entity, double multiplier, int damage) {
        super(
                context,
                ContextCompat.getColor(context, R.color.bullet),
                entity.getPositionX(),
                entity.getPositionY(),
                Constants.BULLET_RADIUS
        );

        this.damage = damage;

        velocityX = entity.getDirectionX() * Constants.MAX_SPEED_BULLET * multiplier;
        velocityY = entity.getDirectionY() * Constants.MAX_SPEED_BULLET * multiplier;
    }

    public void draw(Canvas canvas, GameCamera gameCamera) {
        super.draw(canvas, gameCamera);
    }

    public void move() {
        positionX += velocityX;
        positionY += velocityY;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
