package main.Texture;

import main.Texture.TextureTypes.ImageTexture;
import main.Texture.TextureTypes.UVTestTexture;

import static processing.core.PApplet.append;


public class TextureRegistry {
    public static Texture[] textures = {};
    public static void registerTextures(){
        register(new UVTestTexture());
        register(new ImageTexture("walls.brick"));
    }
    public static void register(Texture texture){
        texture.id = textures.length;
        textures = (Texture[]) append(textures,texture);
    }

    public static Texture get(int i) {
        return textures[i];
    }
}
