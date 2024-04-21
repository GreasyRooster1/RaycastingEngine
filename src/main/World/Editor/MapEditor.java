package main.World.Editor;

import main.Main;
import main.Texture.TextureRegistry;
import main.World.Editor.Buttons.SaveButton;
import main.World.Editor.Buttons.TextureButton;
import main.World.Editor.Buttons.WallButton;
import main.World.Wall;
import main.World.World;
import processing.core.PApplet;

import static main.Util.Util.lineRect;
import static processing.core.PApplet.append;

public class MapEditor {
    private static PApplet app;
    public static UIComponent[] uiComponents={};
    public static boolean placingWall = false;
    public static Wall editingWall;
    public static TextureButton textureButton;

    public static void setup(){
        app = Main.app;
        uiComponents = (UIComponent[])append(uiComponents,new WallButton(10,410,80,80));
        uiComponents = (UIComponent[])append(uiComponents,new TextureButton(100,410,80,80));
        uiComponents = (UIComponent[])append(uiComponents,new SaveButton(190,410,80,80));

        textureButton = (TextureButton) uiComponents[1];
    }

    public static void update(){
        drawBar();
        renderUIComponents();
        checkWallPlace();
        checkWallEdit();
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
            placingWall=false;
        }
    }

    public static void checkWallEdit(){
        for(Wall wall:Main.app.walls){
            if(lineRect(wall.x1,wall.y1,wall.x2,wall.y2,Main.app.mouseX,Main.app.mouseY,4,4)){
                if(Main.app.mousePressed) {
                    if(editingWall!=null) {
                        editingWall.deselect();
                    }
                    editingWall = wall;
                    textureButton.textureId = wall.texture.id;
                }
            }
        }
        if(editingWall!=null){
            editingWall.texture = TextureRegistry.get(textureButton.textureId);
            editingWall.edit();
        }
    }
}
