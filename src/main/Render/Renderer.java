package main.Render;

import main.Main;
import main.Texture.TextureRegistry;
import main.Util.Point;
import main.Util.Ray;
import main.Util.ThreeDimensional.Point3;
import main.Util.ThreeDimensional.Ray3;
import main.Util.ThreeDimensional.Vec3;
import main.World.Player;
import main.World.Wall;

import static main.Render.RenderOptions.*;
import static main.Util.ThreeDimensional.Vec3.dot;
import static processing.core.PApplet.*;


public class Renderer {
    public static float widthRayRatio = Main.app.width / rayCount;
    public static float projectionPlaneDistance = 20;
    public static float projectionPlaneWidth = calculateProjectionWidth();
    public static Wall[] culledWalls = {};
    public static float floorHeight = -.1f;

    public static void renderPlayerView(Player p){
        Main.app.background(fogColor);
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

        float height = renderWall(ray,p,x,ignoredIds,depth);
        //renderFloor(ray,p,x,height);
    }

    public static void renderFloor(Ray ray,Player p,int x,float height){
        for (int i = (int) (Main.app.height/2+(height/2)); i < Main.app.height; i++) {
            Point3 origin = new Point3(p.x,p.y,Main.app.height/2f);
            Vec3 dir = new Vec3(p.x+cos(p.dir)*projectionPlaneDistance+cos(p.dir+PI/2)*((float) x /Main.app.width*projectionPlaneWidth),p.y+sin(p.dir)*projectionPlaneDistance+sin(p.dir-PI/2)*((float) x /Main.app.width*projectionPlaneWidth),i);
            Ray3 floorRay = new Ray3(origin,dir);

            //check ray collision with floor
            Vec3 floorNormal = new Vec3(0,0,1);
            Point3 center = new Point3(0,0,floorHeight);

            float denom = floorNormal.dot(floorRay.direction);

            if (abs(denom) > 0.0001f){
                Vec3 dif = center.sub(floorRay.origin);
                float t = dot(dif,floorNormal) / denom;
                if (t > 0.0001f){
                    //there is a collision!
                    Vec3 normalizedDirection = floorRay.direction.normal();
                    Vec3 multVec = normalizedDirection.mult(t);
                    Point3 collisionPoint = new Point3(floorRay.origin.x+multVec.x,floorRay.origin.y+multVec.y,floorRay.origin.z+multVec.z);
                    println(collisionPoint.x,collisionPoint.y,collisionPoint.z);
                    float segRatio = (250-height/2)/segCount;
                    int col = TextureRegistry.get(1).getColor(ray.collisionWall,collisionPoint.x/250,collisionPoint.y/250);
                    Main.app.fill(0);
                    Main.app.rect(x*(Main.app.width/rayCount),i,Main.app.width/rayCount,segRatio);
                }
            }
        }
    }

    public static float renderWall(Ray ray, Player p, int x, int[] ignoredIds, int depth){
        if(ray.collisionWall.texture.isTransparent){
            Ray rayThroughWall = new Ray(p.x,p.y,ray.dir,maxViewDistance);

            ignoredIds = append(ignoredIds,ray.collisionWall.id);
            rayThroughWall.checkCollisionIgnoringWalls(ignoredIds);
            renderSingleRay(rayThroughWall,p,x,ignoredIds,depth-1);
        }

        float lineHeight = calculateLineHeight(ray,p);
        drawLine(lineHeight,x,ray);
        return lineHeight;
    }

    public static void drawLine(float height, float x, Ray ray){
        Wall wall = ray.collisionWall;

        float wallLength = dist(wall.x1,wall.y1,wall.x2,wall.y2);
        float uv_x = dist(wall.x1,wall.y1,ray.collisionX,ray.collisionY)/wallLength;
        float segRatio = height/segCount;
        float limitedSegCount = segCount;
        if(segRatio<2){
            segRatio = 2;
            limitedSegCount = height/2;
        }

        Main.app.noStroke();
        for(int i=0;i<limitedSegCount;i++){
            float y = 250-height/2 +(segRatio*(i-(wall.height-50)/2));
            if(y<=-segRatio||y>=Main.app.height){
                continue;
            }

            float uv_y = i/limitedSegCount;

            int color = wall.texture.getColor(wall,uv_x,uv_y);
            if(wall.texture.isTransparent&&color==0){
                continue;
            }
            if(ray.mag>=fogDistance){
                color = Main.app.lerpColor(color,Main.app.color(1,0,1), 1-((maxViewDistance-ray.mag)/(maxViewDistance-fogDistance)));
            }
            renderSegment(color,x,i-(wall.height-50)/2,height,segRatio,limitedSegCount);
        }
    }

    public static void renderSegment(int color, float line_x,float line_y,float height,float segRatio,float segC){
        Main.app.fill(color);
        Main.app.rect(widthRayRatio*line_x,250-height/2 +(segRatio*line_y), Main.app.width/ rayCount,height/segC+1);
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
