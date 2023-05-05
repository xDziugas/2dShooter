package com.example.a2dshooter.GameStateManagement;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

public class LoadButton {
    private final DisplayMetrics displayMetrics;
    private final Paint buttonPaint;
    private final Paint borderPaint;
    private final float margin = 10;
    private final float buttonLength = 150;
    private final float buttonWidth = 150;
    float borderLeft, borderTop, borderRight, borderBottom;

    public LoadButton(DisplayMetrics displayMetrics){
        this.displayMetrics = displayMetrics;

        this.borderPaint = new Paint();
        borderPaint.setColor(Color.GRAY);
        borderPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.buttonPaint = new Paint();
        buttonPaint.setColor(Color.YELLOW);
        buttonPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    public void draw(Canvas canvas) {
        borderLeft = displayMetrics.widthPixels * 0.9f - buttonLength / 2;
        borderRight = displayMetrics.widthPixels * 0.9f + buttonLength / 2;
        borderBottom = displayMetrics.heightPixels * 0.17f;
        borderTop = displayMetrics.heightPixels * 0.17f - buttonWidth;

        canvas.drawRect(
                borderLeft,
                borderTop,
                borderRight,
                borderBottom,
                borderPaint
        );

        float innerLeft, innerTop, innerRight, innerBottom;

        innerLeft = borderLeft + margin;
        innerRight = borderRight - margin;
        innerBottom = borderBottom - margin;
        innerTop = borderTop + margin;

        canvas.drawRect(
                innerLeft,
                innerTop,
                innerRight,
                innerBottom,
                buttonPaint
        );

        buttonPaint.setTextSize(35);

        canvas.drawText("Load", borderLeft, borderBottom + 25, buttonPaint);
    }

    public boolean isPressed(float x, float y){
        return x > borderLeft && x < borderRight && y > borderTop && y < borderBottom;
    }

}
