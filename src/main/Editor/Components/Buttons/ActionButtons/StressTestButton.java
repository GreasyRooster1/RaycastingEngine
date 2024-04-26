package main.Editor.Components.Buttons.ActionButtons;

import main.AssetLoader.Asset;
import main.AssetLoader.AssetLoader;
import main.Editor.Components.TextButton;
import main.Editor.File.Loader;
import main.Texture.Texture;
import main.Texture.TextureRegistry;
import main.World.Wall;
import main.World.World;

import static processing.core.PApplet.println;

public class StressTestButton extends TextButton {
    public StressTestButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Reload All";
    }

    @Override
    public void onPress() {
        println("Starting stress test");
        World.clearWorld();
        for (int i = 0; i < 100_000; i++) {
            World.addWall(new Wall(0,0,10,10));
        }
    }
}
