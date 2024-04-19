package main.World.Editor;

import main.Main;
import processing.core.PApplet;

public class MapEditor {
    private static PApplet app;
    public static void open(){
        app = Main.app;
    }

    public static void update(){
        drawBar();
    }
    public static void drawBar(){
        app.noStroke();
        app.fill(.5f,.5f);
        app.rect(0,400,500,100);
    }
}
