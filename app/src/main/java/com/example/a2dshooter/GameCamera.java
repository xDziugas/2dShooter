package com.example.a2dshooter;

import android.graphics.Rect;

import com.example.a2dshooter.gameEntities.Entity;

public class GameCamera {

    public final Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;
    private final double displayCenterX;
    private final double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;

    public GameCamera(int widthPixels, int heightPixels) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);

        displayCenterX = widthPixels / 2.0;
        displayCenterY = heightPixels / 2.0;
    }

    public void update(double centerX, double centerY) {
        gameCenterX = centerX;
        gameCenterY = centerY;

        //player distance to center
        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }

    public double gameToDisplayCoordinatesX(double x) {
        //place object on relatively the same place on the screen, x + player distance moved
        return x + gameToDisplayCoordinatesOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinatesOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenterX - widthPixels / 2),
                (int) (gameCenterY - heightPixels / 2),
                (int) (gameCenterX + widthPixels / 2),
                (int) (gameCenterY + heightPixels / 2)
        );
    }

}
