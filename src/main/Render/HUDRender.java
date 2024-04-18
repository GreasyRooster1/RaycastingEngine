package main.Render;

import main.Main;

public class HUDRender {
    public static void render(){
        Main.app.fill(1);
        Main.app.textSize(16);
        Main.app.text("FPS: "+Main.app.frameRate,10,10);
    }
}
