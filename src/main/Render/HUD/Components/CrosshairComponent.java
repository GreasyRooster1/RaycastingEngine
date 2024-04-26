package main.Render.HUD.Components;

import main.AssetLoader.AssetLoader;
import main.Editor.UIComponent;
import main.Main;
import processing.core.PImage;

public class CrosshairComponent extends UIComponent {
    public int type = 0;
    public CrosshairComponent(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }
    public void render(){
        String imageName = "HUD.crosshair.default";
        if(type==1){
            imageName="HUD.crosshair.selected";
        }
        Main.app.image(AssetLoader.getImage(imageName),x-w/2,y-h/2,w,h);
        type = 0;
    }
}
