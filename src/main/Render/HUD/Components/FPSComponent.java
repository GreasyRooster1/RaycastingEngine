package main.Render.HUD.Components;

import main.AssetLoader.AssetLoader;
import main.Editor.UIComponent;
import main.Main;
import processing.core.PConstants;

public class FPSComponent extends UIComponent {
    public FPSComponent(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }
    public void render(){
        Main.app.textSize(13);
        Main.app.fill(1);
        Main.app.textAlign(PConstants.LEFT,PConstants.TOP);
        Main.app.text("FPS: "+Main.app.frameRate,5,5);
    }
}
