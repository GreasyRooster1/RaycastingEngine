package main.Editor;

import main.Editor.Components.Panels.ExtrasPanel;
import main.Main;
import main.Texture.TextureRegistry;
import main.Util.Point;
import main.Editor.Components.Buttons.ToolButtons.BlockButton;
import main.Editor.Components.Buttons.ToolButtons.PathButton;
import main.Editor.Components.Panels.TexturePanel;
import main.World.Wall;
import main.World.WallTypes.Door;
import main.World.World;
import processing.core.PApplet;

import java.util.ArrayList;

import static main.Util.Util.lineRect;
import static main.World.World.*;
import static processing.core.PApplet.*;
import static processing.core.PConstants.*;

public class MapEditor {
    private static PApplet app;
    public static UIComponent[] uiComponents={};
    public static UIPanel[] uiPanels={};
    public static int barHeight = 400;

    public static int textureId;

    public static float zoom = 1;
    public static float camX = 0;
    public static float camY = 0;
    public static boolean ignoreActions = false;

    public static boolean placingWall = false;
    public static boolean placingPath = false;
    public static boolean moveSpawnpoint = false;
    public static boolean placingBlock = false;
    public static boolean placingDoor = false;

    public static Wall editingWall;
    public static Point[] pathPoints = {};
    public static Point[] doorPoints = {};
    public static float blockSize = 25;
    public static float dotSpacing = 30;

    public static void setup(){
        app = Main.app;
        uiPanels = (UIPanel[]) append(uiPanels,new TexturePanel().sterilize());
        uiPanels = (UIPanel[]) append(uiPanels,new ExtrasPanel().sterilize());

        uiComponents = (UIComponent[])append(uiComponents,new PathButton(100,410,80,80));
        uiComponents = (UIComponent[])append(uiComponents,new BlockButton(190,410,80,80));

    }

    public static void update(){
        //scale sensitive
        drawBar();
        renderUIComponents();

        doZoomPan();

        preformChecksAndRenders();
    }
    public static void renderBackground(){
        Main.app.background(0);
        if(zoom<=.25f) {
            return;
        }
        Main.app.pushMatrix();
        Main.app.scale(zoom);
        Main.app.noStroke();
        float scaledSpacing = dotSpacing*zoom;
        for(int i=0; i<Main.app.width/scaledSpacing; i++){
            for(int j=0; j<Main.app.height/scaledSpacing; j++){
                Main.app.fill(0.3f);
                Main.app.ellipse(i*dotSpacing-(camX%dotSpacing),j*dotSpacing-(camY%dotSpacing),3,3);
            }
        }
        Main.app.popMatrix();
    }
    public static void renderUIComponents(){
        for(UIComponent uiComponent : uiComponents){
            uiComponent.update();
        }
        for(UIComponent panel : uiPanels){
            panel.update();
        }
    }
    public static void drawBar(){
        app.noStroke();
        app.fill(.5f,.5f);
        app.rect(0,400,500,100);
    }

    public static void preformChecksAndRenders(){
        if(checkForOpenPanel()) return;
        if(ignoreActions){
            ignoreActions = false;
            return;
        }

        Main.app.pushMatrix();
        Main.app.scale(zoom);
        Main.app.translate(-camX, -camY);


        checkWallPlace();
        checkWallEdit();
        checkPathPlace();
        checkSpawnMove();
        checkBlockPlace();
        checkDoorPlace();


        Main.app.popMatrix();
    }

    private static boolean checkForOpenPanel() {
        for(UIPanel panel:uiPanels){
            if(panel.active){
                return true;
            }
        }
        return false;
    }

    public static void resetAction(){
        moveSpawnpoint = false;
        editingWall = null;
        placingWall = false;
        placingPath = false;
        placingBlock = false;
        placingDoor = false;
    }

    public static void doZoomPan(){
        if(Main.app.keyPressed){
            if(Main.app.key=='='){
                zoom+=0.01f;
            }
            if(Main.app.key=='-'){
                zoom-=0.01f;
            }
        }
        if(Main.app.mousePressed&&Main.app.mouseButton==CENTER){
            camX-=(Main.app.mouseX-Main.app.pmouseX)/zoom;
            camY-=(Main.app.mouseY-Main.app.pmouseY)/zoom;
        }
    }

    public static void closePanels() {
        for(UIPanel panel : uiPanels){
            if(panel.active) {
                panel.close();
            }
        }
    }

    public static Point getClosestGridSpace(float x,float y){
        float ox = round(x/dotSpacing)*dotSpacing;
        float oy = round(y/dotSpacing)*dotSpacing;
        return new Point(ox,oy);
    }

    public static void checkDoorPlace(){
        if(!placingDoor){ return; }

        if (Main.mouseClicked) {
            if (Main.app.mouseButton == RIGHT&&doorPoints.length>2) {
                Point p1 = doorPoints[0];
                Point p2 = doorPoints[1];
                Point p3 = doorPoints[2];
                Door door = new Door(p1.x, p1.y, p2.x, p2.y);
                door.closeAngle = atan2(p2.y - p1.y, p2.x - p1.x)+2*PI;
                door.openAngle = atan2(p3.y - p1.y, p3.x - p1.x)+2*PI;
                World.addWall(door);
                placingDoor = false;
                doorPoints = new Point[0];
            }
            if(Main.app.mouseButton == LEFT&&doorPoints.length<3){
                doorPoints = (Point[]) append(doorPoints,new Point(Main.mouseXScaled,Main.mouseYScaled));
            }
        }

        if(doorPoints.length == 0) return;
        Main.app.noStroke();
        Main.app.fill(1,0,1,.5f);
        Main.app.ellipse(doorPoints[0].x,doorPoints[0].y,10,10);
        Main.app.stroke(1,0,0,0.5f);

        if(doorPoints.length<=1) return;
        Point p = doorPoints[0];
        Point p1 = doorPoints[1];
        Main.app.line(p.x,p.y,p1.x,p1.y);

        Main.app.noStroke();
        Main.app.fill(1,0,1,.5f);
        Main.app.ellipse(p1.x,p1.y,10,10);

        if(doorPoints.length<=2) return;
        Main.app.fill(1,1,0,.5f);
        Main.app.ellipse(doorPoints[2].x,doorPoints[2].y,10,10);

    }
    public static void checkBlockPlace(){
        if(!placingBlock){ return; }
        if(Main.app.mouseY>barHeight){ return; }

        Main.app.stroke(1,0,0,.5f);
        Main.app.noFill();
        Main.app.rect(Main.mouseXScaled-blockSize/2,Main.mouseYScaled-blockSize/2,blockSize,blockSize);

        blockSize-=Main.mouseScroll*2;

        if(Main.app.mousePressed&&Main.app.mouseButton==LEFT){
            ArrayList<Wall> blockWalls = new ArrayList<>();
            blockWalls.add(newWall(Main.mouseXScaled-blockSize/2,Main.mouseYScaled-blockSize/2,Main.mouseXScaled+blockSize/2,Main.mouseYScaled-blockSize/2));
            blockWalls.add(newWall(Main.mouseXScaled+blockSize/2,Main.mouseYScaled-blockSize/2,Main.mouseXScaled+blockSize/2,Main.mouseYScaled+blockSize/2));
            blockWalls.add(newWall(Main.mouseXScaled+blockSize/2,Main.mouseYScaled+blockSize/2,Main.mouseXScaled-blockSize/2,Main.mouseYScaled+blockSize/2));
            blockWalls.add(newWall(Main.mouseXScaled-blockSize/2,Main.mouseYScaled+blockSize/2,Main.mouseXScaled-blockSize/2,Main.mouseYScaled-blockSize/2));
            for(Wall wall : blockWalls){
                wall.changeTexture(textureId);
            }
            placingBlock=false;
        }
    }
    public static void checkSpawnMove(){
        if(!moveSpawnpoint){return;}
        if(Main.app.mouseY>barHeight){ return; }
        spawnPoint.position.x=Main.mouseXScaled;
        spawnPoint.position.y=Main.mouseYScaled;
        if(Main.mouseClicked){
            moveSpawnpoint = false;
        }
    }
    public static void checkPathPlace(){
        if(!placingPath){ return; }
        renderPath();
        if(Main.app.mouseY>barHeight){ return; }

        if(Main.mouseClicked){
            if(Main.app.mouseButton==LEFT) {
                pathPoints = (Point[]) append(pathPoints, new Point(Main.mouseXScaled, Main.mouseYScaled));
            }else if(Main.app.mouseButton==RIGHT){
                //sterilize points
                for(int i=1;i<pathPoints.length;i++) {
                    Point p1 = pathPoints[i - 1];
                    Point p2 = pathPoints[i];
                    Wall wall = newWall(p1.x,p1.y,p2.x,p2.y);
                    wall.changeTexture(textureId);
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
        if(Main.app.mouseY>barHeight){ return; }

        Main.app.stroke(1,0,0,.5f);
        Main.app.line(Main.mouseXScaled,Main.mouseYScaled,Main.mouseXScaled,Main.mouseYScaled+100);

        if(Main.mouseClicked){
            newWall(Main.mouseXScaled,Main.mouseYScaled,Main.mouseXScaled,Main.mouseYScaled+100);
            placingWall=false;
        }
    }
    public static void checkWallEdit() {
        if (placingWall || placingPath) {
            editingWall = null;
            return;
        }
        if (Main.app.mousePressed&&Main.app.mouseButton==LEFT&&editingWall==null) {
            for (Wall wall : walls) {
                if (lineRect(wall.x1, wall.y1, wall.x2, wall.y2, Main.mouseXScaled-4, Main.mouseYScaled-4, 8, 8)) {
                    editingWall = wall;
                    textureId = wall.texture.id;
                }
            }
        }
        if(Main.app.keyPressed&&(Main.app.key==BACKSPACE||Main.app.key==DELETE)&&editingWall!=null){
            editingWall.delete();
            editingWall= null;
        }
        if(Main.mouseClicked&&Main.app.mouseButton==RIGHT&&editingWall!=null){
            editingWall.deselect();
            editingWall = null;
        }
        if(editingWall!=null){
            editingWall.texture = TextureRegistry.get(textureId);
            editingWall.edit();
        }
    }
}
