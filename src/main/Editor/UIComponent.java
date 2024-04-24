package main.Editor;

import main.Main;

import static main.Util.Util.rectRect;

public class UIComponent {
    public float x,y,w,h;
    public boolean mouseLastFrame = false;
    public UIComponent(float _x, float _y, float _w, float _h){
        x=_x;
        y=_y;
        w=_w;
        h=_h;
    }
    public void update(){
        render();
        checkEvents();
    }
    public void checkEvents(){
        if(rectRect(x,y,w,h,Main.app.mouseX,Main.app.mouseY,1,1)){
            onHover();
            if(Main.app.mousePressed&&!mouseLastFrame){
                onPress();
            }
        }
        mouseLastFrame = Main.app.mousePressed;
    }
    public void render(){
        Main.app.rect(x,y,w,h);
    }
    public void onPress(){

    }
    public void onHover(){

    }
}
