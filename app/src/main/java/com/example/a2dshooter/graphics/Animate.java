package com.example.a2dshooter.graphics;

import android.graphics.Canvas;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.gameEntities.Player;

public class Animate {

    private final Sprite[] playerSpriteArray;
    private final int defaultFrame = 0;
    private int startMovingFrame = 0;
    private int updatesBeforeNextFrame;
    private final int UPDATES_TO_WAIT = 10;

    public Animate(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }

    //todo: galima sukurt klase kuri shiftina framus pvz attact vidury begimo???
    //todo: stop animating when game is over, or make dying animation
    //todo: galima nutrest assetus vietoj piesimo

    public void draw(Canvas canvas, GameCamera gameCamera, Player player) {
        switch (player.getPlayerState().getState()){
            case IDLE:
                drawFrame(canvas, gameCamera, player, playerSpriteArray[defaultFrame]);

                break;
            case START_MOVING:
                updatesBeforeNextFrame = UPDATES_TO_WAIT;
                drawFrame(canvas, gameCamera, player, playerSpriteArray[startMovingFrame]);

                break;
            case IS_MOVING:
                updatesBeforeNextFrame--;
                if(updatesBeforeNextFrame <= 0){
                    updatesBeforeNextFrame = UPDATES_TO_WAIT;
                    toggleStartMovingFrame();
                }
                drawFrame(canvas, gameCamera, player, playerSpriteArray[startMovingFrame]);
                break;

            default:

                break;
        }
    }

    private void toggleStartMovingFrame() {
        if(startMovingFrame < 3)
            startMovingFrame++;
        else
            startMovingFrame = 0;
    }

    public void drawFrame(Canvas canvas, GameCamera gameCamera, Player player, Sprite sprite){
        sprite.draw(
                canvas,
                (int) gameCamera.gameNewX(player.getPositionX()) - sprite.getWidth() / 2,
                (int) gameCamera.gameNewY(player.getPositionY()) - sprite.getHeight() / 2
        );
    }
}
