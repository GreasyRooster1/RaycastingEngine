package main.Texture.TextureTypes;

import main.AssetLoader.AssetLoader;
import main.Texture.Texture;
import main.World.Wall;
import processing.core.PImage;

import static processing.core.PApplet.dist;

public class TiledImageTexture extends Texture {
    PImage image;
    public float tileWidth = 50;
    public TiledImageTexture(String name){
        image = AssetLoader.getImage(name);
    }
    public TiledImageTexture(String name,float w){
        image = AssetLoader.getImage(name);
        tileWidth = w;
    }

    @Override
    public int getColorFromWall(Wall wall, float uv_x, float uv_y) {
        float length = dist(wall.x1,wall.y1,wall.x2,wall.y2);
        float x = ((uv_x*length)%tileWidth)/tileWidth;
        return image.get((int) (x*image.width), (int) (uv_y*image.height));
    }
}
