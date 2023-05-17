package com.example.a2dshooter.gamePanels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.a2dshooter.GameLoop;
import com.example.a2dshooter.R;

/**
 * UI element. Draws current FPS on the screen. Calculated in GameLoop.
 */

public class RefreshRates {

    private final GameLoop gameLoop;
    private final Context context;

    public RefreshRates(Context context, GameLoop gameLoop) {
        this.context = context;
        this.gameLoop = gameLoop;
    }

    public void draw(Canvas canvas) {
        drawFPS(canvas);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString((int) gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 100, paint);
    }

}
