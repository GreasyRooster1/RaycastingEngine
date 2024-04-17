package main.Render;

import main.Main;
import main.Util.Ray;
import main.World.Player;

import static java.lang.Math.*;

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
            drawLine(lineHeight,i,Main.app.color(255,255,0));
        }
    }

    public static void drawLine(float height, float x, int color){
        Main.app.noStroke();
        Main.app.fill(color);
        Main.app.rect(widthRayRatio*x,250-height/2, Main.app.width/ Main.rayCount,height);
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
