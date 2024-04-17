package main.World;

import main.Main;
import main.World.Wall;
import processing.core.PApplet;

import static processing.core.PApplet.append;

public class World {
    public static void createWorld(){
        newWall(100,100,100,200);
        newWall(100,200,200,200);
        newWall(200,200,200,100);
        newWall(200,100,100,100);
    }

    public static void newWall(float x1,float y1,float x2,float y2){
        Main.app.walls = (Wall[]) PApplet.append(Main.app.walls,new Wall(x1,y1,x2,y2));
    }
}
