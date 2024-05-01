package main.Render;

import main.Main;
import main.Util.Colliders.BoxCollider;
import main.World.Player;
import main.World.Wall;

import static main.Render.RenderOptions.fov;
import static main.Render.RenderOptions.maxViewDistance;
import static main.World.World.walls;
import static processing.core.PApplet.*;

public class Optimizer {
    public static BoxCollider cullCollider = new BoxCollider(0,0,0,0);
    public static Wall[] cullWalls(){
        Player p = Main.app.player;
        float ray1x = p.x+cos(p.dir-fov/2)*maxViewDistance;
        float ray1y = p.y+sin(p.dir-fov/2)*maxViewDistance;
        float ray2x = p.x+cos(fov/2+p.dir)*maxViewDistance;
        float ray2y = p.y+sin(fov/2+p.dir)*maxViewDistance;
        float playerx = p.x;
        float playery = p.y;
        cullCollider = new BoxCollider(min3(ray1x,ray2x,playerx),min3(ray1y,ray2y,playery),10,10);
        cullCollider.width = max3(ray1x,ray2x,playerx)-cullCollider.x;
        cullCollider.height = max3(ray1y,ray2y,playery)-cullCollider.y;
        Wall[] culled = {};
        for(Wall wall:walls){
            if(cullCollider.isWallWithin(wall)){
                culled = (Wall[]) append(culled,wall);
            }
        }
        return culled;
    }
    public static float min3(float a, float b, float c){
        return min(min(a,b),c);
    }
    public static float max3(float a, float b, float c){
        return max(max(a,b),c);
    }

    public static float cosOpt(float a){
        return cos(a);
    }
}
