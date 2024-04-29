package main.Texture.TextureTypes;

import main.AssetLoader.AssetLoader;
import main.Main;
import main.Texture.Texture;
import main.World.Wall;
import processing.core.PApplet;
import processing.core.PImage;

import static java.lang.Math.round;
import static processing.core.PApplet.*;

public class GlitchTexture extends Texture {
    public GlitchTexture(){
        isTransparent = true;
    }
    @Override
    public int getColorFromWall(Wall wall, float uv_x, float uv_y) {
        float x = lerp(wall.x1,wall.x2,uv_x);
        float y = lerp(wall.y1,wall.y2,uv_y);
        float effectIntensity = (100-dist(x,y,Main.app.player.x,Main.app.player.y))/100;
        if(effectIntensity<0.01f){
            return 0;
        }
        PImage randomTexture = AssetLoader.assets[Main.app.frameCount%AssetLoader.assets.length].image;
        int color = randomTexture.get((int) (uv_x*randomTexture.width), (int) (uv_y*randomTexture.height));
        float r = Main.app.red(color);
    }
}
