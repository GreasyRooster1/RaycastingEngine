package main.Util.Colliders;

import main.World.Wall;

import static main.Util.Util.lineLine;

public class LineCollider {
    public float x1,y1,x2,y2;
    public LineCollider(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public boolean checkWallCollision(Wall wall) {
        return lineLine(x1,y1,x2,y2, wall.x1, wall.y1, wall.x2, wall.y2).collided;
    }
    public boolean checkLineCollision(float lx1, float ly1, float lx2, float ly2) {
        return lineLine(x1,y1,x2,y2,lx1, ly1, lx2, ly2).collided;
    }
}
