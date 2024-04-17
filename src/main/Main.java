package main;

import main.Render.Renderer;
import main.World.Player;
import main.World.Wall;
import main.World.World;
import processing.core.PApplet;

public class Main extends PApplet {
    public static Main app;
    public static float maxViewDistance = 500;
    public static float rayCount = 100;
    public static boolean mapRender = true;

    public Player player;
    public Wall[] walls = {};
    public boolean[] keys = {false,false,false,false};


    public void settings() {
        size(500, 500);
    }

    public void setup(){
        app = this;
        player = new Player(250,250);
        World.createWorld();
    }

    public void draw(){
        background(64);
        player.move();
        if(mapRender){
            renderMap();
        }else{
            gameRender();
        }
    }

    public void gameRender(){
        Renderer.renderPlayerView(player);
    }

    public void renderMap(){
        player.draw();
        for(Wall wall:walls){
            wall.draw();
        }
    }

    public void keyPressed(){
        if(key=='w'){
            keys[0]=true;
        }
        if(key=='s'){
            keys[1]=true;
        }
        if(key=='a'){
            keys[2]=true;
        }
        if(key=='d'){
            keys[3]=true;
        }
    }

    public void keyReleased(){
        if(key=='w'){
            keys[0]=false;
        }
        if(key=='s'){
            keys[1]=false;
        }
        if(key=='a'){
            keys[2]=false;
        }
        if(key=='d'){
            keys[3]=false;
        }

        if(key==' '){
            mapRender=!mapRender;
        }
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[] {"main.Main"};
        PApplet.main(appletArgs);
    }
}