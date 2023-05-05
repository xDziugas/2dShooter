package com.example.a2dshooter.gameEntities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import com.example.a2dshooter.GameCamera;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    public double positionX;
    public double positionY;
    public double velocityX = 0;
    public double velocityY = 0;
    public double directionX = 1;
    public double directionY = 0;
    public double radius;
    public double slownessEffect = 1;
    public transient Paint paint;
    public transient Context context;

    public Entity(Context context, int color, double positionX, double positionY, double radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.context = context;

        paint = new Paint();
        paint.setColor(color);
    }

    public Entity(Context context, int color, double radius){
        this.radius = radius;
        this.context = context;

        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas, GameCamera gameCamera){
        canvas.drawCircle(
                (float) gameCamera.gameNewX(positionX),
                (float) gameCamera.gameNewY(positionY),
                (float) radius,
                paint
        );
    }

    @NonNull
    @Override
    public String toString() {
        return "Entity{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }


    public void move() {
        positionX += velocityX;
        positionY += velocityY;
    }

    public double getPositionX() { return positionX; }

    public double getPositionY() { return positionY; }

    public double getDirectionX() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public double getRadius(){
        return radius;
    }

}
