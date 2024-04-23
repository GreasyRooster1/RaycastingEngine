package main.Render;

import main.Main;

public class RenderOptions {
    public static int fogColor = 0;
    public static float fogDistance = 800;

    public static float maxViewDistance = 900;
    public static float rayCount = 250;
    public static float segCount = 100;

    public static void setup(){
        fogColor = Main.app.color(64);
    }
}
