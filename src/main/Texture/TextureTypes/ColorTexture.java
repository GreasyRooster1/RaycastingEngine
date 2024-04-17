package main.Texture.TextureTypes;

import main.Texture.Texture;

public class ColorTexture extends Texture {
    int color = 0;
    public ColorTexture(int _color){
        color=_color;
    }
    @Override
    public int getColor(float uv_x, float uv_y) {
        return color;
    }
}
