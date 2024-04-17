package main.Render;

import com.sun.source.doctree.TextTree;
import main.Main;
import main.Texture.Texture;
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

            if(ray.mag>= Main.maxViewDistance){
                continue;
            }

            float lineHeight = calculateLineHeight(ray,p);
            drawLine(lineHeight,i,ray);
        }
    }

    public static void drawLine(float height, float x, Ray ray){

        Wall wall = ray.collisionWall;
        float wallLength = dist(wall.x1,wall.y1,wall.x2,wall.y2);
        float uv_x = dist(wall.x1,wall.y1,ray.collisionX,ray.collisionY)/wallLength;
        int color = wall.texture.getColor(uv_x,0);
        Main.app.noStroke();
        Main.app.fill(color);

        float segRatio = height/Main.segCount;
        for(int i=0;i<Main.segCount;i++){
            Main.app.rect(widthRayRatio*x,250-height/2 +(segRatio*i), Main.app.width/ Main.rayCount,height/Main.segCount+1);
        }
    }

    public static void renderSegment(int x,int y){

    }

    public static float calculateLineHeight(Ray ray,Player p){
        float wallHeight = 50;

        float adjustedLength = (float) (cos(ray.dir-p.dir)*ray.mag);
        float vertical_view = (p.fov/ Main.app.width)* Main.app.height;
        float height = Math.round(Math.min((wallHeight / (2 * tan(0.5f * vertical_view) * adjustedLength)) * Main.app.height, Main.app.height));
        return max(height,0);
    }
    public static void updateRayPosition(Ray ray,Player p){
        ray.x1 = p.x;
        ray.y1 = p.y;
        ray.checkCollision();
    }
}
