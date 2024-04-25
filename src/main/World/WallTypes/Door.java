package main.World.WallTypes;

import main.Main;
import main.World.Wall;

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
        if(dist(x1,y1,Main.app.player.x,Main.app.player.y)<length){
            if(Main.mouseClicked) {
                closed = !closed;
            }
        }
    }

}
