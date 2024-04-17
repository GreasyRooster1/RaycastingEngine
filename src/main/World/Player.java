package main.World;

import main.Main;
import main.Util.Ray;
import processing.core.PApplet;

import static java.lang.Math.*;
import static processing.core.PApplet.append;
import static processing.core.PApplet.radians;

public class Player {
    public float x,y;
    public float dir;
    public float speed,turnSpeed;
    public Ray[] rays={};
    public float fov;

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
            x+= (float) (cos(dir)*speed);
            y+= (float) (sin(dir)*speed);
        }
        if(Main.app.keys[1]) {
            x-= (float) (cos(dir)*speed);
            y-= (float) (sin(dir)*speed);
        }
        if(Main.app.keys[2]){
            dir-=turnSpeed;
            for(Ray ray:rays){
                ray.dir-=turnSpeed;
            }
        }
        if(Main.app.keys[3]){
            dir+=turnSpeed;
            for(Ray ray:rays){
                ray.dir+=turnSpeed;
            }
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

    public void renderWorld(){

    }
}