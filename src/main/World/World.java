package main.World;

import main.Main;
import main.Texture.TextureRegistry;
import main.Texture.TextureTypes.PortalTexture;
import processing.core.PApplet;

import static processing.core.PApplet.append;

public class World {
    public static Wall[] walls = {};
    public static SpawnPoint spawnPoint;

    public static void createWorld(){
        newWall(100,100,100,200);
        newWall(100,200,200,200);
        newWall(200,200,200,100);

        newWall(100,100,130,100);
        newWall(200,100,170,100);

        newWall(130,100,130,50);
        newWall(170,100,170,50);
        newWall(170,50,130,50);

        //portal wall
        Wall portal = newWall(170,100,130,100);
        portal.bottomHeight=15;
        portal.topHeight=5;
        portal.texture = new PortalTexture();
    }

    public static Wall newWall(float x1, float y1, float x2, float y2){
        Wall wall = new Wall(x1,y1,x2,y2);
        walls = (Wall[]) PApplet.append(walls,wall);
        return wall;
    }
}
