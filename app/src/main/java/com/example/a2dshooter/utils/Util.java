package com.example.a2dshooter.utils;

import com.example.a2dshooter.gameEntities.Entity;

public class Util {

    public static double getDistanceBetweenPoints(double p1X, double p1Y, double p2X, double p2Y){
        return Math.sqrt(
                Math.pow(p2X - p1X, 2) +
                Math.pow(p2Y - p1Y, 2)
        );
    }

    public static double getDistanceBetweenObjects(Entity obj1, Entity obj2) {
        return Util.getDistanceBetweenPoints(
                obj1.getPositionX(),
                obj1.getPositionY(),
                obj2.getPositionX(),
                obj2.getPositionY()
        );
    }

    public static boolean isColliding(Entity obj1, Entity obj2) {
        double distance = getDistanceBetweenObjects(obj1, obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        return distance <= distanceToCollision;
    }
}
