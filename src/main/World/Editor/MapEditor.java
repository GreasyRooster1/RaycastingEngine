package main.World.Editor;

import main.Main;
import main.World.Editor.Buttons.WallButton;
import processing.core.PApplet;

import static processing.core.PApplet.append;

public class MapEditor {
    private static PApplet app;
    public static UIComponent[] uiComponents={};

    public static void setup(){
        app = Main.app;
        uiComponents = (UIComponent[])append(uiComponents,new WallButton(10,410,80,80));
    }

    public static void update(){
        drawBar();
        renderUIComponents();
    }
    public static void renderUIComponents(){
        for(UIComponent uiComponent : uiComponents){
            uiComponent.update();
        }
    }
    public static void drawBar(){
        app.noStroke();
        app.fill(.5f,.5f);
        app.rect(0,400,500,100);
    }
}
