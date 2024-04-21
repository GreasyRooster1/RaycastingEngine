package main.Texture.TextureTypes;

import main.AssetLoader.AssetLoader;
import main.Texture.Texture;
import processing.core.PImage;


public class TransparentImageTexture extends Texture {
    PImage image;

    public TransparentImageTexture(String name){
        isTransparent = true;
        image = AssetLoader.getImage(name);
    }

    @Override
    public int getColorFromUV(float uv_x, float uv_y) {
        return image.get((int) (uv_x*image.width), (int) (uv_y*image.height));
    }
}
