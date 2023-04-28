package com.example.a2dshooter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.a2dshooter.gameEntities.Bullet;
import com.example.a2dshooter.gameEntities.Enemy;
import com.example.a2dshooter.gameEntities.Entity;
import com.example.a2dshooter.gameEntities.Player;
import com.example.a2dshooter.gamePanels.GameOver;
import com.example.a2dshooter.gamePanels.Joystick;
import com.example.a2dshooter.gamePanels.RefreshRates;
import com.example.a2dshooter.gamePanels.XpBar;
import com.example.a2dshooter.graphics.Animate;
import com.example.a2dshooter.graphics.SpriteSheet;
import com.example.a2dshooter.map.Tilemap;
import com.example.a2dshooter.utils.Util;

import java.util.ArrayList;
import java.util.Iterator;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private static final int MAX_FRAME_RATE = 60;
    private final RefreshRates refreshRates;
    private GameLoop gameLoop;
    private final Player player;
    private final ArrayList<Enemy> listOfEnemies = new ArrayList<>();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    private final Joystick joystickMovement;
    private final Joystick joystickShoot;
    private final GameCamera gameCamera;
    private final XpBar xpBar;

    private final double GAME_SPEED_MULTIPLIER = 1.1;
    private final int framesToWaitMax = (int) (MAX_FRAME_RATE / GAME_SPEED_MULTIPLIER);

    private int framesToWaitPlayer = framesToWaitMax;
    private int framesToWaitEnemy = framesToWaitMax - 10;

    private final GameOver gameOver;
    private final Tilemap tilemap;

    public Game(Context context){
        super(context);

        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        //game panels
        gameOver = new GameOver(context);
        refreshRates = new RefreshRates(context, gameLoop);
        joystickMovement = new Joystick(330, 700, 120, 60);
        joystickShoot = new Joystick(2000, 700, 120, 60);

        //initialize camera
        gameCamera = new GameCamera(displayMetrics.widthPixels, displayMetrics.heightPixels);

        //entities
        SpriteSheet spriteSheet = new SpriteSheet(context);
        Animate animator = new Animate(spriteSheet.getPlayerSpriteArray());
        player = new Player(context, joystickMovement, joystickShoot, displayMetrics, ContextCompat.getColor(context, R.color.player), gameCamera, animator);
        listOfEnemies.add(new Enemy(context, player, displayMetrics, gameCamera));

        tilemap = new Tilemap(spriteSheet);
        xpBar = new XpBar(displayMetrics, player);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Game", "surfaceCreated: ");
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop.stopLoop();
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.stopLoop();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event actions
        switch (event.getActionMasked()){

            case MotionEvent.ACTION_DOWN:
                int ptrIdx1 = event.findPointerIndex(event.getPointerId(event.getActionIndex()));

                if(joystickShoot.isPressed(event.getX(ptrIdx1), event.getY(ptrIdx1))){
                    joystickShoot.setJoystickPointerId(event.getPointerId(event.getActionIndex()));
                    joystickShoot.setIsPressed(true);
                    player.setCanShoot(true);
                } else if(joystickMovement.isPressed(event.getX(ptrIdx1), event.getY(ptrIdx1))){
                    joystickMovement.setJoystickPointerId(event.getPointerId(event.getActionIndex()));
                    joystickMovement.setIsPressed(true);
                }

                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                int ptrIdx2 = event.findPointerIndex(event.getPointerId(event.getActionIndex()));

                if(joystickShoot.getIsPressed()){
                    if(joystickMovement.isPressed(event.getX(ptrIdx2), event.getY(ptrIdx2))){
                        joystickMovement.setJoystickPointerId(event.getPointerId(event.getActionIndex()));
                        joystickMovement.setIsPressed(true);
                    }
                }else if(joystickMovement.getIsPressed()){
                    if(joystickShoot.isPressed(event.getX(ptrIdx2), event.getY(ptrIdx2))){
                        joystickShoot.setJoystickPointerId(event.getPointerId(event.getActionIndex()));
                        joystickShoot.setIsPressed(true);
                        player.setCanShoot(true);
                    }
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if(joystickMovement.getIsPressed()){
                    int ptrIdx = event.findPointerIndex(joystickMovement.getJoystickPointerId());

                    joystickMovement.setActuator(event.getX(ptrIdx), event.getY(ptrIdx));
                }

                if(joystickShoot.getIsPressed()){
                    int ptrIdx = event.findPointerIndex(joystickShoot.getJoystickPointerId());

                    joystickShoot.setActuator(event.getX(ptrIdx), event.getY(ptrIdx));
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickMovement.getJoystickPointerId() == event.getPointerId(event.getActionIndex())){
                    joystickMovement.setIsPressed(false);
                    joystickMovement.resetActuator();
                }

                if(joystickShoot.getJoystickPointerId() == event.getPointerId(event.getActionIndex())){
                    joystickShoot.setIsPressed(false);
                    joystickShoot.resetActuator();
                    player.setCanShoot(false);
                }

                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas){
        //if super.isactivated
        super.draw(canvas);

        //draw tilemap
        tilemap.draw(canvas, gameCamera);

        //draw game objects
        player.draw(canvas, gameCamera);

        for(Enemy enemy : listOfEnemies){
            enemy.draw(canvas, gameCamera);
            enemy.drawMines(canvas, gameCamera);
        }

        player.drawBullets(canvas, gameCamera);

        //draw game panels
        refreshRates.draw(canvas);
        joystickMovement.draw(canvas);
        joystickShoot.draw(canvas);

        xpBar.draw(canvas);

        if(player.getHealthPoints() <= 0){
            gameOver.draw(canvas);
        }
    }

    public void update() {

        //stop updating if the player is dead
        if(player.getHealthPoints() <= 0){
            return;
        }

        //update game panels
        joystickShoot.update();
        joystickMovement.update();

        player.move();

        if(framesToWaitPlayer <= 0){ //player fire rate (optimize)
            if(player.canShoot()){
                player.shoot();
            }

            framesToWaitPlayer += player.getFireRate();
        }else{
            framesToWaitPlayer--;
        }

        if(framesToWaitEnemy <= 0){ //enemy fire rate / spawn rate (optimize)
            for(Enemy enemy : listOfEnemies){
                enemy.placeMine();
            }

            framesToWaitEnemy += (framesToWaitMax + 20);
            listOfEnemies.add(new Enemy(getContext(), player, displayMetrics, gameCamera));
        }else{
            framesToWaitEnemy--;
        }

        for(Enemy enemy : listOfEnemies){
            enemy.move();
        }

        player.moveBullets();

        //collisions
        Iterator<Enemy> iteratorEnemy = listOfEnemies.iterator();
        while(iteratorEnemy.hasNext()) {
            Enemy enemy = iteratorEnemy.next();
            if (Util.isColliding(enemy, player)) {
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() - 10); //10 - damage
                continue;
            }

            Iterator<Bullet> bulletIterator = player.getBullets().iterator();
            while(bulletIterator.hasNext()){
                Entity bullet = bulletIterator.next();
                if(Util.isColliding(bullet, enemy)){

                    xpBar.setCurrentXp(xpBar.getCurrentXp() + enemy.getXpOnKill());

                    bulletIterator.remove();
                    iteratorEnemy.remove();
                    break;
                }

                if(Util.getDistanceBetweenObjects(bullet, player) > (double) displayMetrics.widthPixels / 2 + 100){ //100 - offset, in case
                    bulletIterator.remove();
                }
            }

            bulletIterator = enemy.getMines().iterator();
            while(bulletIterator.hasNext()){
                Bullet bullet = bulletIterator.next();
                if(Util.isColliding(bullet, player)){

                    bulletIterator.remove();
                    player.setHealthPoints(player.getHealthPoints() - bullet.getDamage());
                    break;
                }

            }

        }
        gameCamera.update(player.getPositionX(), player.getPositionY());

    }

}
