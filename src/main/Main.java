package main;

import main.Render.HUDRender;
import main.Render.RenderOptions;
import main.Render.Renderer;
import main.Texture.TextureRegistry;
import main.World.Editor.File.Loader;
import main.World.Editor.MapEditor;
import main.World.Player;
import main.World.Wall;
import processing.core.PApplet;

import processing.event.MouseEvent;

import static main.AssetLoader.AssetLoader.registerImages;
import static main.Util.Util.moveCursor;
import static main.World.Editor.MapEditor.*;
import static main.World.World.spawnPoint;
import static main.World.World.walls;

public class Main extends PApplet {
    public static Main app;
    public static boolean editRender = true;
    public static boolean previousMouseDown = false;

    public static boolean mouseClicked = false;
    public static float mouseScroll = 0;
    public static float mouseXScaled = 1;
    public static float mouseYScaled = 1;

    public Player player;

    public boolean[] keys = {false,false,false,false};


    public void settings() {
        size(500, 500);
    }

    public void setup(){
        app = this;
        RenderOptions.setup();
        colorMode(RGB,1);

        registerImages();
        TextureRegistry.registerTextures();

        MapEditor.setup();

        //World.createWorld();
        Loader.load("world.json");

        player = new Player(spawnPoint.position.x, spawnPoint.position.y);
    }

    public void draw(){
        background(0.25f);
        updateMouseEvents();
        player.move();
        if(editRender){
            renderEditMode();
        }else{
            gameRender();
        }
        HUDRender.render();
        resetMouseEvents();
    }

    public void gameRender(){
        Renderer.renderPlayerView(player);
    }

    public void renderEditMode(){
        pushMatrix();
        scale(zoom);
        Main.app.scale(zoom);
        Main.app.translate(-camX, -camY);
        player.draw();

        for(Wall wall:walls){
            wall.draw();
        }
        spawnPoint.render();
        popMatrix();

        MapEditor.update();
    }

    public void updateMouseEvents(){
        mouseClicked = !previousMouseDown && mousePressed;
        mouseXScaled = mouseX/ zoom + camX;
        mouseYScaled = mouseY/ zoom + camY;
    }

    public void resetMouseEvents() {
        mouseScroll = 0;
        previousMouseDown = mousePressed;
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

    public void mouseWheel(MouseEvent event) {
        mouseScroll= event.getCount();
    }

    public int randomColor(){
        return Main.app.color(Main.app.random(0,1),Main.app.random(0,1),Main.app.random(0,1));
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[] {"main.Main"};
        PApplet.main(appletArgs);
    }
}