package com.example.a2dshooter.gamePanels;

import static android.content.ContentValues.TAG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;

public class XpBar {

    private final DisplayMetrics displayMetrics;
    private final Paint xpPaint;
    private final Paint borderPaint;
    private float margin = 2;
    private float barLength = 300;
    private float barWidth = 200;
    private float maxXp = 5000;
    private float currentXp = 2500;


    public XpBar(DisplayMetrics displayMetrics){
        this.displayMetrics = displayMetrics;

        this.xpPaint = new Paint();
        xpPaint.setColor(Color.GREEN);

        this.borderPaint = new Paint();
        borderPaint.setColor(Color.RED);

    }

    public void draw(Canvas canvas, int gainXp){

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
        float currentXpPercentage = (currentXp + gainXp) / maxXp;

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


        Log.d(TAG, "draw: Left: " + XpLeft + ", top: " + XpTop + ", right: " + XpRight + ", bottom: " + XpBottom);
    }
}
