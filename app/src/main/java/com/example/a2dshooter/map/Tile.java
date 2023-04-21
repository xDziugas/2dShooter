package com.example.a2dshooter.map;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.example.a2dshooter.graphics.SpriteSheet;

public abstract class Tile {

    protected final Rect mapLocationRect;

    public enum TileType{
        GRASS, LAVA, SQUARES, RED, WATER, EYE
    }

    public Tile(Rect mapLocationRect){
        this.mapLocationRect = mapLocationRect;
    }

    public static Tile getTile(int indexTile, SpriteSheet spriteSheet, Rect mapLocationRect){
        switch (TileType.values() [indexTile]){
            case GRASS:
                return new GrassTile(spriteSheet, mapLocationRect);
            case LAVA:
                return new LavaTile(spriteSheet, mapLocationRect);
            case SQUARES:
                return new SquaresTile(spriteSheet, mapLocationRect);
            case RED:
                return new RedTile(spriteSheet, mapLocationRect);
            case WATER:
                return new WaterTile(spriteSheet, mapLocationRect);
            case EYE:
                return new EyeTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);

}
