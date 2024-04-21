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
        register(new ImageTexture("walls.cobble"));
        register(new ImageTexture("walls.doom.plate"));
        register(new ImageTexture("walls.doom.rock"));
        register(new ImageTexture("walls.doom.satan"));
        register(new ImageTexture("walls.doom.skull"));
        register(new ImageTexture("walls.doom.techDoor"));
        register(new ImageTexture("walls.doom.vaultDoor"));
        register(new ImageTexture("walls.bars"));
    }
    public static void register(Texture texture){
        texture.id = textures.length;
        textures = (Texture[]) append(textures,texture);
    }

    public static Texture get(int i) {
        return textures[i];
    }
}
