package main.Editor.Components.Buttons.ToolButtons;

import main.Editor.Components.TextButton;
import main.Editor.MapEditor;

import java.awt.*;

public class DoorButton extends TextButton{

    public DoorButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
        text = "Create Door";
    }
    public void onPress() {
        MapEditor.resetAction();
        MapEditor.closePanels();
        MapEditor.placingDoor = true;
        MapEditor.ignoreActions = true;
    }
}
