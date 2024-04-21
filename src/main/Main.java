package main;

import main.Render.HUDRender;
import main.Render.Renderer;
import main.Texture.TextureRegistry;
import main.World.Editor.File.Loader;
import main.World.Editor.MapEditor;
import main.World.Player;
import main.World.Wall;
import main.World.World;
import processing.core.PApplet;

import main.AssetLoader.AssetLoader;

import java.awt.*;

import static main.AssetLoader.AssetLoader.registerImages;
import static main.Util.Util.moveCursor;

public class Main extends PApplet {
    public static Main app;
    public static float maxViewDistance = 500;
    public static float rayCount = 250;
    public static float segCount = 100;
    public static boolean editRender = true;
    public static boolean previousMouseDown = false;
    public static boolean mouseClicked = false;

    public Player player;
    public Wall[] walls = {};
    public boolean[] keys = {false,false,false,false};


    public void settings() {
        size(500, 500);
    }

    public void setup(){
        app = this;
        colorMode(RGB,1);

        registerImages();
        TextureRegistry.registerTextures();

        player = new Player(250,250);
        MapEditor.setup();

        //World.createWorld();
        Loader.load("world.json");
    }

    public void draw(){
        background(0.25f);
        updateMouseClick();
        player.move();
        handleCursor();
        if(editRender){
            renderEditMode();
        }else{
            gameRender();
        }
        HUDRender.render();
        previousMouseDown = mousePressed;
    }

    public void gameRender(){
        Renderer.renderPlayerView(player);
    }

    public void renderEditMode(){
        player.draw();

        for(Wall wall:walls){
            wall.draw();
        }
        MapEditor.update();
    }

    public void handleCursor(){
        if(editRender){
            cursor();
        }else{
            noCursor();
            moveCursor(Main.app.width/2,Main.app.height/2);
        }
    }

    public void updateMouseClick(){
        mouseClicked = !previousMouseDown && mousePressed;
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
            editRender =!editRender;
        }
    }

    public int randomColor(){
        return Main.app.color(Main.app.random(0,1),Main.app.random(0,1),Main.app.random(0,1));
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[] {"main.Main"};
        PApplet.main(appletArgs);
    }
}