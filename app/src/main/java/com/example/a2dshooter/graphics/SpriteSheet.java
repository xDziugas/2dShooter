package com.example.a2dshooter.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.a2dshooter.R;

public class SpriteSheet {
    private final Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false; //?
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray(){
        Sprite[] spriteArray = new Sprite[4];
        spriteArray[0] = new Sprite(this, new Rect(0, 0, 64, 64));
        spriteArray[1] = new Sprite(this, new Rect(64, 0, 2*64, 64));
        spriteArray[2] = new Sprite(this, new Rect(2*64, 0, 3*64, 64));
        spriteArray[3] = new Sprite(this, new Rect(3*64, 0, 4*64, 64));

        return spriteArray;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    private Sprite getSpriteByIndex(int indexRow, int indexCol){
        return new Sprite(this, new Rect(
                indexCol * 64, //sprite width = 64
                indexRow * 64, //height 64
                (indexCol + 1) * 64,
                (indexRow + 1) * 64
        ));
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 0);
    }

    public Sprite getEyeSprite() {
        return getSpriteByIndex(2, 0);
    }

    public Sprite getWaterSprite() {
        return getSpriteByIndex(1, 4);
    }

    public Sprite getRedSprite() {
        return getSpriteByIndex(1, 3);
    }

    public Sprite getSquaresSprite() {
        return getSpriteByIndex(1, 2);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(1, 1);
    }
}
