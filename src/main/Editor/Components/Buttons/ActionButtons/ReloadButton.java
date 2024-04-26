package main.Editor.Components.Buttons.ActionButtons;

import main.AssetLoader.Asset;
import main.AssetLoader.AssetLoader;
import main.Editor.Components.TextButton;
import main.Editor.File.Loader;
import main.Texture.Texture;
import main.Texture.TextureRegistry;
import main.World.World;

import static processing.core.PApplet.println;

public class ReloadButton extends TextButton {
    public ReloadButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Reload All";
    }

    @Override
    public void onPress() {
        println("Reloading world...");
        World.clearWorld();
        Loader.load("world.json");

        println("Reloading Assets");
        AssetLoader.assets = new Asset[0];
        AssetLoader.registerImages();

        println("Reloading Textures");
        TextureRegistry.textures = new Texture[0];
        TextureRegistry.registerTextures();
    }
}
