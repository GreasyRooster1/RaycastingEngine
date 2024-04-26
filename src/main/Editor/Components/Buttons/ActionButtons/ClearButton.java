package main.Editor.Components.Buttons.ActionButtons;

import main.Editor.Components.TextButton;
import main.Editor.File.Loader;
import main.World.World;

import java.awt.*;

public class ClearButton extends TextButton {
    public ClearButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Clear Map";
    }

    @Override
    public void onPress() {
        World.clearWorld();
    }
}
