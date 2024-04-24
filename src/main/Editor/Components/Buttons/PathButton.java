package main.Editor.Components.Buttons;

import main.Editor.Components.TextButton;
import main.Editor.MapEditor;

public class PathButton extends TextButton {
    public PathButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Create Path";
    }

    @Override
    public void onPress() {
        MapEditor.resetAction();
        MapEditor.placingPath = true;
    }
}
