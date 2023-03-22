package com.example.a2dshooter.gameEntities;

import androidx.annotation.NonNull;

import com.example.a2dshooter.utils.Util;

public interface EntityGeneral {

    static double getDistanceBetweenObjects(Entity obj1, Entity obj2) {
        return Util.getDistanceBetweenPoints(
                obj1.getPositionX(),
                obj1.getPositionY(),
                obj2.getPositionX(),
                obj2.getPositionY()
        );
    }

    static boolean isColliding(Entity obj1, Entity obj2) {
        double distance = getDistanceBetweenObjects(obj1, obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        return distance <= distanceToCollision;
    }

    @NonNull
    @Override
    String toString();
}
