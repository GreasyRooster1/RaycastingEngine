package main.Render.HUD;

import main.Editor.UIComponent;
import main.Main;
import main.Render.HUD.Components.CrosshairComponent;
import main.Render.HUD.Components.FPSComponent;
import processing.core.PConstants;

import static processing.core.PApplet.append;

public class HUDRender {
    public static UIComponent[] uiComponents = {};
    public static UIComponent crosshair;
    public static void setup(){
        uiComponents = (UIComponent[]) append(uiComponents,new CrosshairComponent(250,250,8,8));
        uiComponents = (UIComponent[]) append(uiComponents,new FPSComponent(250,250,10,10));

        crosshair = uiComponents[0];
    }
    public static void render(){
        for(UIComponent component:uiComponents){
            component.update();
        }
    }
}
