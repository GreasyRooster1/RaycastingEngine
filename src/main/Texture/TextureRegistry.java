package main.Texture;

import main.Main;
import main.Texture.TextureTypes.ErrorTexture;
import main.Texture.TextureTypes.GradientTexture;
import main.Texture.TextureTypes.ImageTexture;
import main.Texture.TextureTypes.UVTestTexture;

import static processing.core.PApplet.append;


public class TextureRegistry {
    public static Texture[] textures = {};
    public static void registerTextures(){
        register(new ErrorTexture());
        register(new UVTestTexture());
        register(new ImageTexture("walls.brick"));
        register(new GradientTexture(Main.app.color(1, 0.6f, 0.9f),Main.app.color(0.6f, 0.9f, 1)));
    }
    public static void register(Texture texture){
        texture.id = textures.length;
        textures = (Texture[]) append(textures,texture);
    }

    public static Texture get(int i) {
        return textures[i];
    }
}
