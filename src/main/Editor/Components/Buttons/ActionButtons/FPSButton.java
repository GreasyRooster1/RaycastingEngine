package main.Editor.Components.Buttons.ActionButtons;

import main.AssetLoader.Asset;
import main.AssetLoader.AssetLoader;
import main.Editor.Components.TextButton;
import main.Editor.File.Loader;
import main.Main;
import main.Texture.Texture;
import main.Texture.TextureRegistry;
import main.World.World;

import static processing.core.PApplet.println;

public class FPSButton extends TextButton {
    public boolean capped = true;
    public FPSButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Uncap FPS";
    }

    @Override
    public void onPress() {
        capped = !capped;
        if(capped) {
            Main.app.frameRate(60);
            text = "Uncap FPS";
        }else{
            Main.app.frameRate(9999);
            text = "Cap FPS";
        }
    }
}
