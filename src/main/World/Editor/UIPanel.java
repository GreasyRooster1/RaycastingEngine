package main.World.Editor;

import main.Main;
import main.World.Editor.Components.TextButton;

import static processing.core.PApplet.append;

public class UIPanel extends UIComponent {
    public boolean active = false;
    public UIComponent[] components;

    public UIPanel() {
        super(0,0, Main.app.width, Main.app.height);
    }

    public void open(){
        active = true;
        onOpen();
    }
    public void close(){
        active = false;
        onClose();
    }

    public void update(){
        if(!active) return;
        renderBackground();
        updateComponents();
        checkEvents();
    }

    public void renderBackground(){
        Main.app.noStroke();
        Main.app.fill(.5f,.5f);
        Main.app.rect(x,y,w,h);
    }

    public void updateComponents(){
        for(UIComponent component : components){
            component.update();
        }
    }

    public void addComponent(UIComponent component){
        components = (UIComponent[]) append(component,components);
    }

    public TextButton createAccessorButton(){
        return null;
    }
    public void createComponents(){

    }
    public void onOpen(){

    }
    public void onClose(){

    }
}
