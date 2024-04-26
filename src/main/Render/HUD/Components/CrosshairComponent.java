package main.Render.HUD.Components;

import main.AssetLoader.AssetLoader;
import main.Editor.UIComponent;
import main.Main;

public class CrosshairComponent extends UIComponent {
    public CrosshairComponent(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }
    public void render(){
        Main.app.image(AssetLoader.getImage("HUD.crosshair.default"),x-w/2,y-h/2,w,h);
    }
}
