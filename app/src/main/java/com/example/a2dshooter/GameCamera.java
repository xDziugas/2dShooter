package com.example.a2dshooter;

import android.graphics.Rect;

/**
 * Game camera, contains methods to keep player in the center of the screen
 */

public class GameCamera {

    private final transient Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private double offsetX;
    private double offsetY;
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
        offsetX = displayCenterX - gameCenterX;
        offsetY = displayCenterY - gameCenterY;
    }

    public double gameNewX(double x) {
        //place object on relatively the same place on the screen, x + player distance moved
        return x + offsetX;
    }

    public double gameNewY(double y) {
        return y + offsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenterX - widthPixels / 2),
                (int) (gameCenterY - heightPixels / 2),
                (int) (gameCenterX + widthPixels / 2),
                (int) (gameCenterY + heightPixels / 2)
        );
    }

    public Rect getDISPLAY_RECT() {
        return DISPLAY_RECT;
    }
}
