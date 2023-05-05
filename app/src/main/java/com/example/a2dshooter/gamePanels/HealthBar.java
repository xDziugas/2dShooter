package com.example.a2dshooter.gamePanels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.R;
import com.example.a2dshooter.gameEntities.Entity;

import java.io.Serializable;

public class HealthBar implements Serializable {

    public transient Paint borderPaint;
    public transient Paint healthPaint;
    public transient Entity entity;
    private final int width, height, margin;
    private final int max_health;
    public transient GameCamera gameCamera;


    public HealthBar(Context context, Entity entity, int max_health, GameCamera gameCamera){
        this.entity = entity;
        this.width = 100;
        this.height = 20;
        this.margin = 2;

        this.max_health = max_health;

        this.borderPaint = new Paint();
        this.healthPaint = new Paint();

        this.gameCamera = gameCamera;

        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        int healthColor = ContextCompat.getColor(context, R.color.health);

        borderPaint.setColor(borderColor);
        healthPaint.setColor(healthColor);
    }

    public void draw(Canvas canvas, int current_health){
        float x = (float) entity.getPositionX();
        float y = (float) entity.getPositionY();
        float distanceToPlayer = 62;
        float healthPointPercentage = (float) current_health / max_health;

        //draw border (floats)
        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = x - width / 2f;
        borderRight = x + width / 2f;
        borderBottom = y - distanceToPlayer;
        borderTop = borderBottom - height;

        canvas.drawRect(
                (float) gameCamera.gameNewX(borderLeft),
                (float) gameCamera.gameNewY(borderTop),
                (float) gameCamera.gameNewX(borderRight),
                (float) gameCamera.gameNewY(borderBottom),
                borderPaint
        );


        //draw health
        float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;
        healthWidth = width - 2 * margin;
        healthHeight = height - 2 * margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth * healthPointPercentage;
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;

        canvas.drawRect(
                (float) gameCamera.gameNewX(healthLeft),
                (float) gameCamera.gameNewY(healthTop),
                (float) gameCamera.gameNewX(healthRight),
                (float) gameCamera.gameNewY(healthBottom),
                healthPaint
        );

    }

}
