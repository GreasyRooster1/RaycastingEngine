package main.World;

import main.Main;
import main.Texture.Texture;
import main.Texture.TextureRegistry;
import main.Texture.TextureTypes.*;
import processing.core.PConstants;

import static main.Util.Util.rectRect;
import static main.Util.WallColorHelper.getWallColor;
import static processing.core.PApplet.append;

public class Wall {
    public float x1,y1;
    public float x2,y2;
    public Texture texture;
    public int selectedHandle = 0;
    public float bottomHeight,topHeight;
    public float height;
    public float yShift;
    public int id;

    public Wall(float _x1,float _y1,float _x2,float _y2){
        x1=_x1;
        y1=_y1;
        x2=_x2;
        y2=_y2;
        height = 50;
        yShift = 0;
        id= (int) Main.app.random(PConstants.MIN_INT,PConstants.MAX_INT);
        texture = TextureRegistry.get(1);
    }

    public void update() {
        everyFrame();
    }

    public void draw(){
        Main.app.stroke(getWallColor(texture.id));
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
        if(Main.app.mousePressed&&rectRect(Main.mouseXScaled,Main.mouseYScaled,1,1,x1-5,y1-5,10,10)){
            selectedHandle = 1;
        }
        if(Main.app.mousePressed&&rectRect(Main.mouseXScaled,Main.mouseYScaled,1,1,x2-5,y2-5,10,10)){
            selectedHandle = 2;
        }
        if(!Main.app.mousePressed){
            selectedHandle=0;
        }
        if(selectedHandle==1){
            x1=Main.mouseXScaled;
            y1=Main.mouseYScaled;
        }
        if(selectedHandle==2){
            x2=Main.mouseXScaled;
            y2=Main.mouseYScaled;
        }
    }

    public void drawEditMode(){
        Main.app.noStroke();
        Main.app.fill(1,1,0);
        Main.app.strokeWeight(2);
        Main.app.ellipse(x1,y1,10,10);
        Main.app.ellipse(x2,y2,10,10);
    }

    public void changeTexture(int id){
        texture=TextureRegistry.get(id);
    }

    public void delete() {
        x1=0;
        y1=0;
        x2=0;
        y2=0;
        height = 0;
        Wall[] tmp = {};
        for(Wall wall:World.walls){
            if(wall.id!=id){
                tmp = (Wall[]) append(tmp,wall);
            }
        }
        World.walls = tmp;
    }

    public void everyFrame(){

    }
}
