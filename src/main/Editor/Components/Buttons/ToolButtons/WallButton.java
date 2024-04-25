package main.Editor.Components.Buttons.ToolButtons;

import main.Editor.Components.TextButton;
import main.Editor.MapEditor;

public class WallButton extends TextButton {

    public WallButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Place Wall";
    }

    @Override
    public void onPress() {
        MapEditor.closePanels();
        MapEditor.resetAction();
        MapEditor.placingWall = true;
        MapEditor.ignoreActions = true;
    }
}
