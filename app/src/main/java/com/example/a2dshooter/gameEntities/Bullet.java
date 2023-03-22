package com.example.a2dshooter.gameEntities;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.R;

public class Bullet extends Entity{

    private int damage;

    public Bullet(Context context, Entity entity, double multiplier){
        super(
                context,
                ContextCompat.getColor(context, R.color.bullet),
                entity.getPositionX(),
                entity.getPositionY(),
                BULLET_RADIUS
        );

        setDamage(MAX_BULLET_DAMAGE);

        velocityX = entity.getDirectionX() * MAX_SPEED_BULLET * multiplier;
        velocityY = entity.getDirectionY() * MAX_SPEED_BULLET * multiplier;
    }

    public void draw(Canvas canvas, GameCamera gameCamera){
        super.draw(canvas, gameCamera);
    }

    public void move() { //not needed probably
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
