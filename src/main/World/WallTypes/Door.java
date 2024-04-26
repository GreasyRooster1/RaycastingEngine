package main.World.WallTypes;

import main.Main;
import main.World.Wall;

import static main.Util.Util.rectRect;
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
        float angle = lerp(closeAngle,openAngle,animationProgress);
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
        Main.app.stroke(0,0,1);
        Main.app.line(x1,y1,x1+cos(openAngle)*length,y1+sin(openAngle)*length);
        Main.app.stroke(0,1,1);
        Main.app.line(x1,y1,x1+cos(closeAngle)*length,y1+sin(closeAngle)*length);
    }

    public void drawEditMode(){
        Main.app.noStroke();
        Main.app.fill(1,1,0);
        Main.app.ellipse(x1,y1,10,10);
        Main.app.fill(0,1,1);
        Main.app.ellipse(x1+cos(openAngle)*(length),y1+sin(openAngle)*(length),10,10);
        Main.app.ellipse(x1+cos(closeAngle)*(length),y1+sin(closeAngle)*(length),10,10);
    }

    public void checkHandleMove(){
        if(Main.app.mousePressed&&rectRect(Main.mouseXScaled,Main.mouseYScaled,1,1,x1-5,y1-5,10,10)){
            selectedHandle = 1;
        }
        //no p2 editing
        if(Main.app.mousePressed&&rectRect(Main.mouseXScaled,Main.mouseYScaled,1,1,(x1+cos(openAngle)*(length))-5,(y1+sin(openAngle)*(length))-5,10,10)){
            selectedHandle = 3;
        }
        if(Main.app.mousePressed&&rectRect(Main.mouseXScaled,Main.mouseYScaled,1,1,(x1+cos(closeAngle)*(length))-5,(y1+sin(closeAngle)*(length))-5,10,10)){
            selectedHandle = 4;
        }
        if(!Main.app.mousePressed){
            selectedHandle=0;
        }
        if(selectedHandle==1){
            x1=Main.mouseXScaled;
            y1=Main.mouseYScaled;
        }
        if(selectedHandle==3){
            length = dist(x1,y1,Main.mouseXScaled,Main.mouseYScaled);
            openAngle = atan2(Main.mouseYScaled-y1,Main.mouseXScaled-x1) +PI*2;
        }
        if(selectedHandle==4){
            length = dist(x1,y1,Main.mouseXScaled,Main.mouseYScaled);
            closeAngle = atan2(Main.mouseYScaled-y1,Main.mouseXScaled-x1) +PI*2;
        }
    }
}
