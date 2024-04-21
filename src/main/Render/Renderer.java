package main.Render;

import main.Main;
import main.Util.Ray;
import main.World.Player;
import main.World.Wall;

import static java.lang.Math.*;
import static processing.core.PApplet.dist;

public class Renderer {
    public static float widthRayRatio = Main.app.width / Main.rayCount;
    public static void renderPlayerView(Player p){
        for(int i = 0; i< Main.rayCount; i+=1){
            Ray ray = p.rays[i];
            updateRayPosition(ray,p);


            renderSingleRay(ray,p,i);
        }
    }

    public static void renderSingleRay(Ray ray,Player p,int x){
        if(ray.mag>= Main.maxViewDistance){
            return;
        }

        if(ray.collisionWall.texture.isTransparent){
            float endX = (float)(p.x+cos(ray.dir)* Main.maxViewDistance);
            float endY = (float)(p.y+sin(ray.dir)* Main.maxViewDistance);
            Ray rayThroughWall = new Ray(p.x,p.y,endX,endY);
            rayThroughWall.checkCollisionIgnoringWall(ray.collisionWall.UUID);
            renderSingleRay(rayThroughWall,p,x);
        }

        float lineHeight = calculateLineHeight(ray,p);
        drawLine(lineHeight,x,ray);
    }

    public static void drawLine(float height, float x, Ray ray){
        Wall wall = ray.collisionWall;

        float wallLength = dist(wall.x1,wall.y1,wall.x2,wall.y2);
        float uv_x = dist(wall.x1,wall.y1,ray.collisionX,ray.collisionY)/wallLength;
        float segRatio = height/Main.segCount;

        Main.app.noStroke();
        for(int i=0;i<Main.segCount;i++){
            float y = 250-height/2 +(segRatio*i);
            if(y<=-segRatio||y>=Main.app.height){
                continue;
            }

            float uv_y = i/Main.segCount;

            int color = wall.texture.getColor(wall,uv_x,uv_y);
            if(wall.texture.isTransparent&&color==0){
                continue;
            }
            renderSegment(color,x,i,height,segRatio);
        }
    }

    public static void renderSegment(int color, float line_x,float line_y,float height,float segRatio){
        Main.app.fill(color);
        Main.app.rect(widthRayRatio*line_x,250-height/2 +(segRatio*line_y), Main.app.width/ Main.rayCount,height/Main.segCount+1);
    }

    public static float calculateLineHeight(Ray ray,Player p){
        float wallHeight = ray.collisionWall.height;

        float adjustedLength = (float) (cos(ray.dir-p.dir)*ray.mag);
        float vertical_view = (p.fov/ Main.app.width)* Main.app.height;
        float height = Math.round((wallHeight / (2 * tan(0.5f * vertical_view) * adjustedLength)) * Main.app.height);
        return max(height,0);
    }

    public static void updateRayPosition(Ray ray,Player p){
        ray.x1 = p.x;
        ray.y1 = p.y;
        ray.checkCollision();
    }
}
