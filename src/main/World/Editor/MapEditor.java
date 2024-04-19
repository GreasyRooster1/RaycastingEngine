package main.World.Editor;

import main.Main;
import main.World.Editor.Buttons.WallButton;
import main.World.World;
import processing.core.PApplet;

import static processing.core.PApplet.append;

public class MapEditor {
    private static PApplet app;
    public static UIComponent[] uiComponents={};
    public static boolean placingWall = false;

    public static void setup(){
        app = Main.app;
        uiComponents = (UIComponent[])append(uiComponents,new WallButton(10,410,80,80));
    }

    public static void update(){
        drawBar();
        renderUIComponents();
        checkWallPlace();
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

    public static void checkWallPlace(){
        if(!placingWall){ return; }
        if(Main.app.mouseY>400){ return; }

        Main.app.stroke(1,0,0,.5f);
        Main.app.line(Main.app.mouseX,Main.app.mouseY,Main.app.mouseX,Main.app.mouseY+100);

        if(Main.app.mousePressed){
            World.newWall(Main.app.mouseX,Main.app.mouseY,Main.app.mouseX,Main.app.mouseY+100);
            //placingWall=false;
        }
    }
}
