package main.World;

import main.Main;
import main.Texture.Texture;
import main.Texture.TextureRegistry;
import main.Texture.TextureTypes.*;

import static main.Util.Util.rectRect;

public class Wall {
    public float x1,y1;
    public float x2,y2;
    public Texture texture;
    public int selectedHandle = 0;

    public Wall(float _x1,float _y1,float _x2,float _y2){
        x1=_x1;
        y1=_y1;
        x2=_x2;
        y2=_y2;
        texture = TextureRegistry.get(1);
    }

    public void draw(){
        Main.app.stroke(255,0,0);
        Main.app.strokeWeight(2);
        Main.app.line(x1,y1,x2,y2);
    }

    public void edit(){
        drawEditMode();
        checkHandleMove();
    }
    public void deselect(){
        selectedHandle = 0;
    }

    public void checkHandleMove(){
        if(Main.app.mousePressed&&rectRect(Main.app.mouseX,Main.app.mouseY,1,1,x1-5,y1-5,10,10)){
            selectedHandle = 1;
        }
        if(Main.app.mousePressed&&rectRect(Main.app.mouseX,Main.app.mouseY,1,1,x2-5,y2-5,10,10)){
            selectedHandle = 2;
        }
        if(!Main.app.mousePressed){
            selectedHandle=0;
        }
        if(selectedHandle==1){
            x1=Main.app.mouseX;
            y1=Main.app.mouseY;
        }
        if(selectedHandle==2){
            x2=Main.app.mouseX;
            y2=Main.app.mouseY;
        }
    }

    public void drawEditMode(){
        Main.app.noStroke();
        Main.app.strokeWeight(2);
        Main.app.fill(255,255,0);
        Main.app.ellipse(x1,y1,10,10);
        Main.app.ellipse(x2,y2,10,10);
    }
}
