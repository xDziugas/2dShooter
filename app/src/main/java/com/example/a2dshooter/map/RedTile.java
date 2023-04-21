package com.example.a2dshooter.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.a2dshooter.graphics.Sprite;
import com.example.a2dshooter.graphics.SpriteSheet;


public class RedTile extends Tile {
    private final Sprite sprite;

    public RedTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getRedSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
