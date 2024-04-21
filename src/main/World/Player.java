package main.World;

import main.Main;
import main.Util.Ray;
import processing.core.PApplet;

import static java.lang.Math.*;
import static main.Util.Util.lineLine;
import static processing.core.PApplet.*;
import static processing.core.PConstants.PI;

public class Player {
    public float x,y;
    public float dir;
    public float speed,turnSpeed;
    public Ray[] rays={};
    public float fov;
    public boolean useMouse = false;
    public float collisionDistance = 10;

    public Player(float _x, float _y){
        x=_x;
        y=_y;
        dir= 0;
        speed = 3;
        turnSpeed=radians(1);
        fov = radians(45);
        setupRays();
    }
    public void setupRays(){
        for (float i = -fov/2; i <fov/2 ; i+=fov/ Main.rayCount) {
            rays = (Ray[]) PApplet.append(rays,new Ray(x,y,i, Main.maxViewDistance));
        }
    }

    public void draw(){
        Main.app.stroke(0,0,255);
        Main.app.strokeWeight(1);
        Main.app.line(x,y, (float) (x+cos(dir)*40), (float) (y+sin(dir)*40));
        Main.app.fill(0,255,255);
        Main.app.noStroke();
        Main.app.ellipse(x,y,20,20);

        drawRays();
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
    }

    public void turn(float deg){
        dir+=deg;
        for(Ray ray:rays){
            ray.dir+=deg;
        }
    }

    public void drawRays(){
        for(Ray ray:rays){
            ray.x1 = x;
            ray.y1 = y;
            ray.checkCollision();
            ray.draw();
        }
    }

    public void attemptMove(float dx, float dy) {
        boolean hitX = false;
        boolean hitY = false;
        for (Wall wall : Main.app.walls) {
            if (lineLine(wall.x1, wall.y1, wall.x2, wall.y2, x, y, x + dx*collisionDistance, y).collided) {
                hitX = true;
            }
            if (lineLine(wall.x1, wall.y1, wall.x2, wall.y2, x, y, x, y + dy*collisionDistance).collided) {
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
}
