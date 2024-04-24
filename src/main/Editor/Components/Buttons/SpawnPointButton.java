package main.Editor.Components.Buttons;

import main.Editor.Components.TextButton;
import main.Editor.MapEditor;

public class SpawnPointButton extends TextButton {
    public SpawnPointButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Move Spawn";
    }

    @Override
    public void onPress() {
        MapEditor.closePanels();
        MapEditor.resetAction();
        MapEditor.moveSpawnpoint = true;
    }
}