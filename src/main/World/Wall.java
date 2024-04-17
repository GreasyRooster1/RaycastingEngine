package main.World;

import main.Main;
import main.Texture.Texture;
import main.Texture.TextureTypes.ColorTexture;
import main.Texture.TextureTypes.GradientTexture;

public class Wall {
    public float x1,y1;
    public float x2,y2;
    public Texture texture;

    public Wall(float _x1,float _y1,float _x2,float _y2){
        x1=_x1;
        y1=_y1;
        x2=_x2;
        y2=_y2;
        texture = new GradientTexture(Main.app.randomColor(),Main.app.randomColor());
    }

    public void draw(){
        Main.app.stroke(255,0,0);
        Main.app.strokeWeight(2);
        Main.app.line(x1,y1,x2,y2);
    }
}
