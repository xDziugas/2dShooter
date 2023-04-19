package com.example.a2dshooter.gamePanels;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import com.example.a2dshooter.gameEntities.Player;

public class XpBar {

    private final DisplayMetrics displayMetrics;
    private final Paint xpPaint;
    private final Paint borderPaint;
    private final Paint lvlPaint;
    private float margin = 2;
    private float barLength = 900;
    private float barWidth = 25;
    private float[] maxXp = {200, 300, 400, 500, 600, 700, 2000, 3000, 5000, 10000, 15000, 20000};
    private float currentXp = 0;
    private Player player;

    public XpBar(DisplayMetrics displayMetrics, Player player){
        this.displayMetrics = displayMetrics;

        this.xpPaint = new Paint();
        xpPaint.setColor(Color.BLUE);
        xpPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.borderPaint = new Paint();
        borderPaint.setColor(Color.GRAY);
        borderPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.lvlPaint = new Paint();
        lvlPaint.setColor(Color.WHITE);
        lvlPaint.setTextSize(75);

        this.player = player;
    }

    public void draw(Canvas canvas) {

        //draw border
        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = displayMetrics.widthPixels / 2f - barLength / 2;
        borderRight = displayMetrics.widthPixels / 2f + barLength / 2;
        borderBottom = displayMetrics.heightPixels;
        borderTop = displayMetrics.heightPixels - barWidth;

        canvas.drawRect(
                borderLeft,
                borderTop,
                borderRight,
                borderBottom,
                borderPaint
        );

        //draw xp
        float XpLeft, XpTop, XpRight, XpBottom, XpLength, XpWidth;
        float currentXpPercentage = currentXp / maxXp[player.getLevel()];

        if(currentXp >= maxXp[player.getLevel()]){
            currentXp -= maxXp[player.getLevel()];
            if(player.getLevel() < maxXp.length - 1){
                player.incLevel();
            }
        }

        XpLength = barLength - 2 * margin;
        XpWidth = barWidth - 2 * margin;
        XpLeft = borderLeft + margin;
        XpRight = XpLeft + XpLength * currentXpPercentage;
        XpBottom = borderBottom - margin;
        XpTop = XpBottom - XpWidth;

        canvas.drawRect(
                XpLeft,
                XpTop,
                XpRight,
                XpBottom,
                xpPaint
        );

        canvas.drawText(String.valueOf(player.getLevel()), borderLeft, borderTop - 50, lvlPaint);

    }

    public float getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(float currentXp) {
        this.currentXp = currentXp;
    }

    public float[] getXpRequirements(){
        return this.maxXp;
    }

}
