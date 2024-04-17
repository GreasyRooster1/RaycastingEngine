package main.Render;

import main.Main;

public class RenderOptions {
    public static int fogColor = 0;
    public static int floorColor = 0;
    public static int skyColor = 0;

    public void setup(){
        fogColor = Main.app.color(64);
        floorColor = Main.app.color(0,0,255);
        skyColor = Main.app.color(0,255,255);
    }
}