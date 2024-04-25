package main.World;

import main.Main;
import main.Util.Colliders.BoxCollider;
import main.Util.Colliders.LineCollider;
import main.Util.Ray;
import processing.core.PApplet;

import javax.sound.sampled.Line;

import static main.Render.RenderOptions.*;
import static main.World.World.walls;
import static processing.core.PApplet.*;
import static processing.core.PConstants.PI;

public class Player {
    public float x,y;
    public float dir;
    public float speed,turnSpeed;
    public Ray[] rays={};
    public BoxCollider physicalCollider;
    public LineCollider interactionCollider;

    public boolean useMouse = false;

    public Player(float _x, float _y){
        x=_x;
        y=_y;
        dir= 0;
        speed = 3;
        turnSpeed=radians(1);

        physicalCollider=new BoxCollider(x-5,y-5,10,10);
        interactionCollider=new LineCollider(x-40,y-40,80,80);

        setupRays();
    }
    public void setupRays(){
        for (float i = -fov/2; i <fov/2 ; i+=fov/ rayCount) {
            rays = (Ray[]) PApplet.append(rays,new Ray(x,y,i, maxViewDistance));
        }
    }

    public void draw(){
        drawRays();
        drawSelf();
        physicalCollider.render(1,0,0);
        interactionCollider.render(0,1,0);
    }
    public void drawSelf(){
        Main.app.fill(0,255,255);
        Main.app.noStroke();
        Main.app.ellipse(x,y,20,20);
    }
    public void drawRays(){
        for(Ray ray:rays){
            ray.x1 = x;
            ray.y1 = y;
            ray.checkCollision();
            ray.draw();
        }
    }

    public void move(){
        if(Main.app.keys[0]) {
            attemptMove(
                    cos(dir)*speed,
                    sin(dir)*speed
            );
        }
        if(Main.app.keys[1]) {
            attemptMove(
                    -cos(dir)*speed,
                    -sin(dir)*speed
            );
        }

        if(!Main.editRender&&useMouse){
            float delta = Main.app.mouseX- (float)(Main.app.width /2) +8;
            turn(delta/100);
            if(Main.app.keys[2]){
                attemptMove(
                        -cos(dir-PI/2)*speed,
                        -sin(dir-PI/2)*speed
                );
            }
            if(Main.app.keys[3]){
                attemptMove(
                        cos(dir-PI/2)*speed,
                        sin(dir-PI/2)*speed
                );
            }
        }else{
            if(Main.app.keys[2]){
                turn(-turnSpeed);
            }
            if(Main.app.keys[3]){
                turn(turnSpeed);
            }
        }
        updateColliders();
    }

    public void turn(float deg){
        dir+=deg;
        for(Ray ray:rays){
            ray.dir+=deg;
        }
    }

    public void attemptMove(float dx, float dy) {
        boolean hitX = false;
        boolean hitY = false;
        for (Wall wall : walls) {
            if (physicalCollider.checkShiftedWallCollision(wall,dx,0)) {
                hitX = true;
            }
            if (physicalCollider.checkShiftedWallCollision(wall,0,dy)) {
                hitY = true;
            }
            if (hitX && hitY) {
                break;
            }
        }
        if (!hitX) {
            x += dx;
        }
        if (!hitY) {
            y += dy;
        }
    }

    public void updateColliders(){
        physicalCollider.x = x- physicalCollider.width/2;
        physicalCollider.y = y- physicalCollider.height/2;

        interactionCollider.x1 = x;
        interactionCollider.y1 = y;
        interactionCollider.x2 = x+cos(dir)*80;
        interactionCollider.y2 = y+sin(dir)*80;
    }
}
