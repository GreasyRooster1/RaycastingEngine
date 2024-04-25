package main.Util;

import main.Main;

import static main.Util.Util.lineLine;
import static main.Util.Util.lineRect;

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
}
