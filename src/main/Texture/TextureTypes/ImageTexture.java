package main.Texture.TextureTypes;

import main.AssetLoader.AssetLoader;
import main.Texture.Texture;
import processing.core.PImage;


public class ImageTexture extends Texture {
    PImage image;

    public ImageTexture(String name){
        image = AssetLoader.getImage(name);
    }

    @Override
    public int getColor(float uv_x, float uv_y) {
        return image.get((int) (uv_x*image.width), (int) (uv_y*image.height));
    }
}
