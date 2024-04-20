package main.Texture.TextureTypes;

import main.Main;
import main.Texture.Texture;

import static java.lang.Math.round;

public class ErrorTexture extends Texture {
    public ErrorTexture(){

    }
    @Override
    public int getColorFromUV(float uv_x, float uv_y) {
        if(uv_x>0.5&&uv_y<=0.5){
            return Main.app.color(255,0,255);
        }
        if(uv_x<=0.5&&uv_y>0.5){
            return Main.app.color(255,0,255);
        }
        return 0;
    }
}
