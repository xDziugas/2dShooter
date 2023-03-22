package com.example.a2dshooter.utils;

public class Util {

    public static double getDistanceBetweenPoints(double p1X, double p1Y, double p2X, double p2Y){
        return Math.sqrt(
                Math.pow(p2X - p1X, 2) +
                Math.pow(p2Y - p1Y, 2)
        );
    }
}
