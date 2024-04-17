package main.Texture.TextureTypes;

import main.Main;
import main.Texture.Texture;

public class VerticalGradientTexture extends Texture {
    int col1 = 0;
    int col2 = 0;
    public VerticalGradientTexture(int _col1, int _col2){
        col1 = _col1;
        col2 = _col2;
    }
    @Override
    public int getColor(float uv_x, float uv_y) {
        return Main.app.lerpColor(col1,col2, uv_y);
    }
}
