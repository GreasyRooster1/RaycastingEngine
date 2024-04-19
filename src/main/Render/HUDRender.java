package main.Render;

import main.Main;
import processing.core.PConstants;

public class HUDRender {
    public static void render(){
        Main.app.fill(1);
        Main.app.textSize(16);
        Main.app.textAlign(PConstants.LEFT);
        Main.app.text("FPS: "+Main.app.frameRate,5,15);
    }
}
