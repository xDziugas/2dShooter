package com.example.a2dshooter;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    public static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;

    private final SurfaceHolder surfaceHolder;
    private final Game game;

    private boolean isRunning = false;
    private double averageUPS;
    private double averageFPS;


    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;

    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public void startLoop() {
        Log.d("GameLoop", "startLoop: ");
        isRunning = true;

        String state = this.getState().toString();
        Log.d("GameLoop", "state: " + state);

        if(this.getState() == State.NEW)
            this.start();

    }

    @Override
    public void run() {
        super.run();

        //time and cycle count vars
        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        //gameloop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning){

            //try to update and render
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                    }
                }
            }

            //pause game loop to not exceed target ups
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //skip frames to keep up with target ups
            while(sleepTime < 0 && updateCount < MAX_UPS-1) {
                game.update();

                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount * UPS_PERIOD - elapsedTime);
            }

            //calculate average ups and fps
            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                averageUPS  = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void stopLoop() {
        Log.d("GameLoop", "stoptLoop: ");

        isRunning = false;

        //wait for thread to join
        try{
            join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
