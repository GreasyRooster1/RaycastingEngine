package main.Util;

import main.Main;

import java.awt.*;

public class Util {
    public static CollisionResult lineLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        CollisionResult result = new CollisionResult();
        // calculate the distance to intersection point
        float uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
        float uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));

        // if uA and uB are between 0-1, lines are colliding
        if (uA > 0 && uA <= 1 && uB > 0 && uB <= 1) {

            // optionally, draw a circle where the lines meet
            float intersectionX = x1 + (uA * (x2-x1));
            float intersectionY = y1 + (uA * (y2-y1));

            result.collided = true;
            result.intersectionX = intersectionX;
            result.intersectionY = intersectionY;
        }
        return result;
    }
    public static boolean rectRect(float r1x, float r1y, float r1w, float r1h, float r2x, float r2y, float r2w, float r2h) {

        // are the sides of one rectangle touching the other?

        if (r1x + r1w >= r2x &&    // r1 right edge past r2 left
                r1x <= r2x + r2w &&    // r1 left edge past r2 right
                r1y + r1h >= r2y &&    // r1 top edge past r2 bottom
                r1y <= r2y + r2h) {    // r1 bottom edge past r2 top
            return true;
        }
        return false;
    }
    public static boolean lineRect(float x1, float y1, float x2, float y2, float rx, float ry, float rw, float rh) {

        // check if the line has hit any of the rectangle's sides
        // uses the Line/Line function below
        boolean left =   lineLine(x1,y1,x2,y2, rx,ry,rx, ry+rh).collided;
        boolean right =  lineLine(x1,y1,x2,y2, rx+rw,ry, rx+rw,ry+rh).collided;
        boolean top =    lineLine(x1,y1,x2,y2, rx,ry, rx+rw,ry).collided;
        boolean bottom = lineLine(x1,y1,x2,y2, rx,ry+rh, rx+rw,ry+rh).collided;

        // if ANY of the above are true, the line
        // has hit the rectangle
        return left || right || top || bottom;
    }

    public static void moveCursor(int x,int y){
        try {
            Robot robot = new Robot();
            robot.mouseMove(Main.app.windowX+x,Main.app.windowY+y);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
