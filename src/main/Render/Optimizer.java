package main.Render;

import main.Main;
import main.Util.Colliders.BoxCollider;
import main.World.Player;
import main.World.Wall;

import static main.World.World.walls;
import static processing.core.PApplet.*;

public class Optimizer {
    public static Wall[] cullWalls(){
        Player p = Main.app.player
        BoxCollider cullCollider = new BoxCollider(0,0,0,0);
        cullCollider.x = min(Main.app.player.x+cos())
        for(Wall wall:walls){
            if()
        }
    }
}
