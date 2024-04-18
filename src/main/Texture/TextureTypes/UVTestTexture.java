package main.Texture.TextureTypes;

import main.Main;
import main.Texture.Texture;

public class UVTestTexture extends Texture {
    public UVTestTexture(){

    }
    public int getColor(float uv_x, float uv_y) {
        return Main.app.color(uv_x, uv_y,0);
    }
}
