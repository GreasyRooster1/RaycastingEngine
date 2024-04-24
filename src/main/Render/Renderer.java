package main.Render;

import main.Main;
import main.Util.Point;
import main.Util.Ray;
import main.World.Player;
import main.World.Wall;

import static java.lang.Math.*;
import static main.Render.RenderOptions.*;
import static processing.core.PApplet.append;
import static processing.core.PApplet.dist;

public class Renderer {
    public static float widthRayRatio = Main.app.width / rayCount;
    public static float projectionPlaneDistance = 20;
    public static float projectionPlaneWidth = calculateProjectionWidth();

    public static void renderPlayerView(Player p){
        for(int i = 0; i< rayCount; i+=1){
            Ray ray = p.rays[i];
            updateRayPosition(ray,p);
            
            renderSingleRay(ray,p,i,new int[0],3);
        }
    }

    public static void renderSingleRay(Ray ray,Player p,int x,int[] ignoredIds,int depth){
        if(ray.mag>= maxViewDistance||depth<=0){
            return;
        }

        if(ray.collisionWall.texture.isTransparent){
            Ray rayThroughWall = new Ray(p.x,p.y,ray.dir,maxViewDistance);

            ignoredIds = append(ignoredIds,ray.collisionWall.id);
            rayThroughWall.checkCollisionIgnoringWalls(ignoredIds);
            renderSingleRay(rayThroughWall,p,x,ignoredIds,depth-1);
        }

        float lineHeight = calculateLineHeight(ray,p);
        drawLine(lineHeight,x,ray);
    }

    public static void drawLine(float height, float x, Ray ray){
        Wall wall = ray.collisionWall;

        float wallLength = dist(wall.x1,wall.y1,wall.x2,wall.y2);
        float uv_x = dist(wall.x1,wall.y1,ray.collisionX,ray.collisionY)/wallLength;
        float segRatio = height/segCount;

        Main.app.noStroke();
        for(int i=0;i<segCount;i++){
            float y = 250-height/2 +(segRatio*i);
            if(y<=-segRatio||y>=Main.app.height){
                continue;
            }

            float uv_y = i/segCount;

            int color = wall.texture.getColor(wall,uv_x,uv_y);
            if(ray.mag>=fogDistance){
                color = Main.app.lerpColor(color,fogColor, 1-((maxViewDistance-ray.mag)/(maxViewDistance-fogDistance)));
            }
            if(wall.texture.isTransparent&&color==0){
                continue;
            }
            renderSegment(color,x,i,height,segRatio);
        }
    }

    public static void renderSegment(int color, float line_x,float line_y,float height,float segRatio){
        Main.app.fill(color);
        Main.app.rect(widthRayRatio*line_x,250-height/2 +(segRatio*line_y), Main.app.width/ rayCount,height/segCount+1);
    }

    public static float calculateLineHeight(Ray ray,Player p){
        float wallHeight = ray.collisionWall.height;

        float adjustedLength = (float) (cos(ray.dir-p.dir)*ray.mag);
        float vertical_view = (fov/ Main.app.width)* Main.app.height;
        float height = Math.round((wallHeight / (2 * tan(0.5f * vertical_view) * adjustedLength)) * Main.app.height);
        return max(height,0);
    }

    public static void updateRayPosition(Ray ray,Player p){
        ray.x1 = p.x;
        ray.y1 = p.y;
        ray.checkCollision();
    }

    public static float calculateProjectionWidth(){
        Point leftmost = new Point((float) (cos(-fov/2)*projectionPlaneDistance), (float) (sin(-fov/2)*projectionPlaneDistance));
        Point rightmost = new Point((float) (cos(fov/2)*projectionPlaneDistance), (float) (sin(fov/2)*projectionPlaneDistance));
        return dist(leftmost.x,leftmost.y,rightmost.x, rightmost.y);
    }
}
