package com.example.a2dshooter.utils;

import com.example.a2dshooter.GameLoop;

public class Constants {
    static public final double SPAWNS_PER_MINUTE = 20;
    static public final double BULLET_RADIUS = 10;
    static public final double MAX_SPEED_BULLET = 15;
    static public final int MAX_BULLET_DAMAGE = 60;
    static public final int MAX_MINE_DAMAGE = 30;
    static public final double SPEED_PIXELS_PER_SECOND = 600.0;
    static public final double MAX_SPEED_PER_FRAME_PLAYER = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    static public final double MAX_SPEED_PER_FRAME_ENEMY = 0.7 * SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    static public final int MAX_HEALTH_POINTS_PLAYER = 1000;
    static public final int MAX_HEALTH_POINTS_ENEMY = 100;
    static public final int ENEMY_SPAWN_POSITION_OFFSET = 100;
    static public final int ENEMY_RADIUS = 30;
    static public final int PLAYER_RADIUS = 35;
    static public final int XP_ON_KILL_ENEMY = 50;

}
