package main.World.WallTypes;

import main.Main;
import main.World.Wall;

import static main.Util.WallColorHelper.getWallColor;
import static processing.core.PApplet.*;

public class Door extends Wall {
    public float openAngle = 0;
    public float closeAngle = radians(90);
    public float length = 0;
    public float animationProgress = 0;
    public boolean closed = true;
    public Door(float _x1, float _y1, float _x2, float _y2) {
        super(_x1, _y1, _x2, _y2);
        length = dist(x1,y1,x2,y2);
    }
    public void everyFrame(){
        animate();
        checkForOpen();
    }
    public void updateEndpoints(){
        float angle = lerp(openAngle,closeAngle,animationProgress);
        x2 = x1+cos(angle)*length;
        y2 = y1+sin(angle)*length;
    }

    public void animate(){
        if(!closed&&animationProgress<1){
            animationProgress+=0.01f;
        }
        if(closed&&animationProgress>0){
            animationProgress-=0.01f;
        }
        updateEndpoints();
    }

    public void checkForOpen(){
        if(Main.mouseClicked) {
            if(Main.app.player.interactionCollider.checkWallCollision(this)) {
                closed = !closed;
            }
        }
    }

    @Override
    public void draw() {
        Main.app.stroke(getWallColor(texture.id));
        Main.app.strokeWeight(2);
        Main.app.line(x1,y1,x2,y2);
        Main.app.noFill();
        //Main.app.arc(x1,y1,length*2,length*2,openAngle,closeAngle);
        Main.app.stroke(0,0,1,.25f);
        Main.app.line(x1,y1,x1+cos(openAngle)*length,y1+sin(openAngle)*length);
        Main.app.line(x1,y1,x1+cos(closeAngle)*length,y1+sin(closeAngle)*length);
    }
}
