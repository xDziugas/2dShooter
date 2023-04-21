package com.example.a2dshooter.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.a2dshooter.graphics.Sprite;
import com.example.a2dshooter.graphics.SpriteSheet;


public class WaterTile extends Tile {
    private final Sprite sprite;

    public WaterTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getWaterSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
