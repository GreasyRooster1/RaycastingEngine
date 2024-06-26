package main.Util.Colliders;

import main.Main;
import main.World.Wall;

import static main.Util.Util.lineRect;
import static main.Util.Util.rectRect;

public class BoxCollider {
    public float x,y,width,height;
    public BoxCollider(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(float r,float g,float b) {
        Main.app.stroke(r,g,b);
        Main.app.noFill();
        Main.app.rect(x,y,width,height);
    }

    public boolean checkCollisionLine(float x1,float y1,float x2,float y2){
        return lineRect(x1,y1,x2,y2,x,y,width,height);
    }
    public boolean checkWallCollision(Wall wall){
        return lineRect(wall.x1, wall.y1,wall.x2,wall.y2,x,y,width,height);
    }
    public boolean checkShiftedWallCollision(Wall wall,float dx,float dy){
        return lineRect(wall.x1, wall.y1,wall.x2,wall.y2,x+dx,y+dy,width,height);
    }
    public boolean isWallWithin(Wall wall){
        if(rectRect(wall.x1,wall.y1,1,1,x,y,width,height)){
            return true;
        }
        if(rectRect(wall.x2,wall.y2,1,1,x,y,width,height)){
            return true;
        }
        if(lineRect(wall.x1,wall.y1,wall.x2,wall.y2,x,y,width,height)){
            return true;
        }
        return false;
    }
}
