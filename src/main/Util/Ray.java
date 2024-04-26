package main.Util;

import main.Main;
import main.World.Wall;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static main.Render.RenderOptions.maxViewDistance;
import static main.Render.Renderer.culledWalls;
import static main.World.World.walls;
import static processing.core.PApplet.dist;
import static processing.core.PApplet.println;

public class Ray {
    public float x1,y1;
    public float dir,mag;
    public Wall collisionWall;
    public float collisionX,collisionY;

    public Ray(float _x1, float _y1, float _dir, float _mag){
        x1=_x1;
        y1=_y1;
        dir=_dir;
        mag=_mag;
    }

    public void update(){
        checkCollision();
        draw();
    }

    public void draw(){
        Main.app.stroke(255,255,0);
        Main.app.strokeWeight(1);
        Main.app.line(x1,y1, (float) (x1+cos(dir)*mag), (float) (y1+sin(dir)*mag));
    }
    public void checkCollisionIgnoringWalls(int[] ids) {
        float closestCollisionDistance=maxViewDistance;
        Wall hitWall = null;
        for(Wall wall: culledWalls) {
            boolean dontHit = false;
            for(int id:ids){
                if(wall.id==id){
                    dontHit = true;
                    break;
                }
            }
            if(dontHit){
                continue;
            }
            CollisionResult result = Util.lineLine(x1, y1, (float) (x1 + cos(dir) * closestCollisionDistance), (float) (y1 + sin(dir) * closestCollisionDistance), wall.x1,wall.y1,wall.x2,wall.y2);
            if(result.collided){
                mag = dist(x1,y1,result.intersectionX,result.intersectionY);
                closestCollisionDistance=mag;
                hitWall = wall;
                collisionX = result.intersectionX;
                collisionY = result.intersectionY;
            }
        }
        collisionWall= hitWall;
        mag = closestCollisionDistance;
    }
    public void checkCollision(){
        float closestCollisionDistance=maxViewDistance;
        Wall hitWall = null;
        for(Wall wall: culledWalls) {
            CollisionResult result = Util.lineLine(x1, y1, (float) (x1 + cos(dir) * closestCollisionDistance), (float) (y1 + sin(dir) * closestCollisionDistance), wall.x1,wall.y1,wall.x2,wall.y2);
            if(result.collided){
                mag = dist(x1,y1,result.intersectionX,result.intersectionY);
                closestCollisionDistance=mag;
                hitWall = wall;
                collisionX = result.intersectionX;
                collisionY = result.intersectionY;
            }
        }
        collisionWall= hitWall;
        mag = closestCollisionDistance;
    }
}
