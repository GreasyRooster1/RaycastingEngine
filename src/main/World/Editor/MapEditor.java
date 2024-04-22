package main.World.Editor;

import main.Main;
import main.Texture.TextureRegistry;
import main.Util.Point;
import main.World.Editor.Buttons.*;
import main.World.Wall;
import main.World.World;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static main.Util.Util.lineRect;
import static main.World.World.*;
import static processing.core.PApplet.append;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;

public class MapEditor {
    private static PApplet app;
    public static UIComponent[] uiComponents={};

    public static TextureButton textureButton;

    public static boolean placingWall = false;
    public static boolean placingPath = false;
    public static boolean moveSpawnpoint = false;
    public static boolean placingBlock = false;

    public static Wall editingWall;
    public static Point[] pathPoints = {};
    public static float blockSize = 25;

    public static void setup(){
        app = Main.app;
        uiComponents = (UIComponent[])append(uiComponents,new WallButton(10,410,80,80));
        uiComponents = (UIComponent[])append(uiComponents,new PathButton(100,410,80,80));
        uiComponents = (UIComponent[])append(uiComponents,new TextureButton(190,410,80,80));
        uiComponents = (UIComponent[])append(uiComponents,new SpawnPointButton(280,410,80,80));
        uiComponents = (UIComponent[])append(uiComponents,new BlockButton(370,410,80,80));

        uiComponents = (UIComponent[])append(uiComponents,new SaveButton(410,410,80,80));

        textureButton = (TextureButton) uiComponents[2];
    }

    public static void update(){
        drawBar();
        renderUIComponents();
        checkWallPlace();
        checkWallEdit();
        checkPathPlace();
        checkSpawnMove();
        checkBlockPlace();
    }
    public static void renderUIComponents(){
        for(UIComponent uiComponent : uiComponents){
            uiComponent.update();
        }
    }
    public static void drawBar(){
        app.noStroke();
        app.fill(.5f,.5f);
        app.rect(0,400,500,100);
    }

    public static void checkBlockPlace(){
        if(!placingBlock){ return; }
        if(Main.app.mouseY>400){ return; }

        Main.app.stroke(1,0,0,.5f);
        Main.app.rect(Main.app.mouseX-blockSize/2,Main.app.mouseY-blockSize/2,blockSize,blockSize);

        blockSize+=Main.mouseScroll;

        if(Main.app.mousePressed){
            ArrayList<Wall> blockWalls = new ArrayList<>();
            blockWalls.add(newWall(Main.app.mouseX-blockSize/2,Main.app.mouseY-blockSize/2,Main.app.mouseX+blockSize/2,Main.app.mouseY-blockSize/2));
            blockWalls.add(newWall(Main.app.mouseX+blockSize/2,Main.app.mouseY-blockSize/2,Main.app.mouseX+blockSize/2,Main.app.mouseY+blockSize/2));
            blockWalls.add(newWall(Main.app.mouseX+blockSize/2,Main.app.mouseY+blockSize/2,Main.app.mouseX-blockSize/2,Main.app.mouseY+blockSize/2));
            blockWalls.add(newWall(Main.app.mouseX-blockSize/2,Main.app.mouseY+blockSize/2,Main.app.mouseX-blockSize/2,Main.app.mouseY-blockSize/2));
            for(Wall wall : blockWalls){
                wall.changeTexture(textureButton.textureId);
            }
            placingBlock=false;
        }
    }

    public static void checkSpawnMove(){
        if(!moveSpawnpoint){return;}
        if(Main.app.mouseY>400){ return; }
        spawnPoint.position.x=Main.app.mouseX;
        spawnPoint.position.y=Main.app.mouseY;
        if(Main.app.mousePressed){
            moveSpawnpoint = false;
        }
    }

    public static void checkPathPlace(){
        if(!placingPath){ return; }
        renderPath();
        if(Main.app.mouseY>400){ return; }

        if(Main.mouseClicked){
            if(Main.app.mouseButton==LEFT) {
                pathPoints = (Point[]) append(pathPoints, new Point(Main.app.mouseX, Main.app.mouseY));
            }else if(Main.app.mouseButton==RIGHT){
                //sterilize points
                for(int i=1;i<pathPoints.length;i++) {
                    Point p1 = pathPoints[i - 1];
                    Point p2 = pathPoints[i];
                    Wall wall = newWall(p1.x,p1.y,p2.x,p2.y);
                    wall.changeTexture(textureButton.textureId);
                }
                placingPath = false;
                pathPoints = new Point[0];
            }
        }

    }
    public static void renderPath(){
        if(pathPoints.length==0){ return; }
        Main.app.noStroke();
        Main.app.fill(1,0,1,.5f);
        Main.app.ellipse(pathPoints[0].x,pathPoints[0].y,10,10);
        for(int i=1;i<pathPoints.length;i++){
            Point p1 = pathPoints[i-1];
            Point p2 = pathPoints[i];
            Main.app.stroke(1,0,0,.5f);
            Main.app.line(p1.x,p1.y,p2.x,p2.y);

            Main.app.noStroke();
            Main.app.fill(0,1,1,.5f);
            Main.app.ellipse(p2.x,p2.y,10,10);
        }
    }

    public static void checkWallPlace(){
        if(!placingWall){ return; }
        if(placingPath){ placingWall = false; return;}
        if(Main.app.mouseY>400){ return; }

        Main.app.stroke(1,0,0,.5f);
        Main.app.line(Main.app.mouseX,Main.app.mouseY,Main.app.mouseX,Main.app.mouseY+100);

        if(Main.app.mousePressed){
            newWall(Main.app.mouseX,Main.app.mouseY,Main.app.mouseX,Main.app.mouseY+100);
            placingWall=false;
        }
    }

    public static void checkWallEdit(){
        if(placingWall||placingPath){ editingWall=null;return; }
        for(Wall wall:walls){
            if(lineRect(wall.x1,wall.y1,wall.x2,wall.y2,Main.app.mouseX,Main.app.mouseY,4,4)){
                if(Main.app.mousePressed) {
                    if(editingWall!=null) {
                        editingWall.deselect();
                    }
                    editingWall = wall;
                    textureButton.textureId = wall.texture.id;
                }
            }
        }
        if(editingWall!=null){
            editingWall.texture = TextureRegistry.get(textureButton.textureId);
            editingWall.edit();
        }
    }

    public static void resetAction(){
        moveSpawnpoint = false;
        editingWall = null;
        placingWall = false;
        placingPath = false;
        placingBlock = false;
    }
}
