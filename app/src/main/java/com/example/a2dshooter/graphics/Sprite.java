package com.example.a2dshooter.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Draws a frame on the screen. Used with Animator.
 */

public class Sprite {


    private final com.example.a2dshooter.graphics.SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(com.example.a2dshooter.graphics.SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(x - 32, y - 32, x + getWidth() + 32, y + getHeight() + 32),
                null
        );
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }

}
