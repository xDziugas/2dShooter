package com.example.a2dshooter.gameEntities;

import android.graphics.Canvas;

import com.example.a2dshooter.GameCamera;
import com.example.a2dshooter.GameLoop;

public interface EntityPrimary extends EntityGeneral{

    //Bullet constants
    double BULLET_RADIUS = 10;
    double MAX_SPEED_BULLET = 15;
    int MAX_BULLET_DAMAGE = 60;
    double SPEED_PIXELS_PER_SECOND = 600.0;

    //Enemy constants
    int MAX_HEALTH_POINTS_ENEMY = 100;
    int ENEMY_SPAWN_POSITION_OFFSET = 100;
    double SPAWNS_PER_MINUTE = 20;
    int MAX_MINE_DAMAGE = 30;
    double MAX_SPEED_PER_FRAME_ENEMY = 0.7 * SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    int ENEMY_RADIUS = 30;
    int XP_ON_KILL_ENEMY = 50;

    //Player constants
    double MAX_SPEED_PER_FRAME_PLAYER = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    int MAX_HEALTH_POINTS_PLAYER = 1000;
    int PLAYER_RADIUS = 35;

    double getPositionX();

    double getPositionY();

    double getDirectionX();

    double getDirectionY();

    double getRadius();

}
