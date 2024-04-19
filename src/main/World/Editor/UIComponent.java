package main.World.Editor;

import main.Main;

import static main.Util.Util.rectRect;

public class UIComponent {
    public float x,y,w,h;
    public UIComponent(float _x, float _y, float _w, float _h){
        x=_x;
        y=_y;
        w=_w;
        h=_h;
    }
    public void update(){
        render();
    }
    public void checkEvents(){
        if(rectRect(x,y,w,h,Main.app.mouseX,Main.app.mouseY,1,1)){
            onHover();
            if(Main.app.mousePressed){
                onPress();
            }
        }
    }
    public void render(){
        Main.app.rect(x,y,w,h);
    }
    public void onPress(){

    }
    public void onHover(){

    }
}
