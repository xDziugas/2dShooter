package com.example.a2dshooter.map;

import static com.example.a2dshooter.map.MapLayout.MAP_COLUMNS;
import static com.example.a2dshooter.map.MapLayout.MAP_ROWS;
import static com.example.a2dshooter.map.MapLayout.TILE_HEIGHT;
import static com.example.a2dshooter.map.MapLayout.TILE_WIDTH;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.graphics.SpriteSheet;


public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private final SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public Tilemap(SpriteSheet spriteSheet){
        mapLayout = new MapLayout();
        this.spriteSheet = spriteSheet;
        initializeTilemap();
    }

    private void initializeTilemap() {
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[MAP_ROWS][MAP_COLUMNS];
        for(int row = 0; row < MAP_ROWS; row++){
            for(int col = 0; col < MAP_COLUMNS; col++){
                tilemap[row][col] = Tile.getTile(
                        layout[row][col],
                        spriteSheet,
                        getRectByIndex(row, col)
                );
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                MAP_COLUMNS * TILE_WIDTH,
                MAP_ROWS * TILE_HEIGHT,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);
        for(int row = 0; row < MAP_ROWS; row++){
            for(int col = 0; col < MAP_COLUMNS; col++){
                tilemap[row][col].draw(mapCanvas);
            }
        }
    }

    private Rect getRectByIndex(int indexRow, int indexCol){
        return new Rect(
                indexCol * TILE_WIDTH, //tile width = 64
                indexRow * TILE_HEIGHT, //height 64
                (indexCol + 1) * 64,
                (indexRow + 1) * 64
        );
    }

    public void draw(Canvas canvas, GameCamera gameCamera) {
        canvas.drawBitmap(
                mapBitmap,
                gameCamera.getGameRect(),
                gameCamera.getDISPLAY_RECT(),
                null
        );
    }
}
