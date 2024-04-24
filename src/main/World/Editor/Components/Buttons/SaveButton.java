package main.World.Editor.Components.Buttons;

import main.Main;
import main.World.Editor.Components.TextButton;
import main.World.Editor.File.Loader;
import main.World.Editor.UIComponent;
import processing.core.PConstants;

public class SaveButton extends TextButton {
    public SaveButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Save";
    }

    @Override
    public void onPress() {
        Loader.save("world.json");
    }
}