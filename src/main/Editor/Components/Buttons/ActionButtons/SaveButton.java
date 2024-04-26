package main.Editor.Components.Buttons.ActionButtons;

import main.Editor.Components.TextButton;
import main.Editor.File.Loader;

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