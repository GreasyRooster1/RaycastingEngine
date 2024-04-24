package main.Editor.Components.Buttons;

import main.Editor.Components.TextButton;
import main.Editor.MapEditor;

public class BlockButton extends TextButton {
    public BlockButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);

        text = "Create Block";
    }

    @Override
    public void onPress() {
        MapEditor.resetAction();
        MapEditor.placingBlock = true;
    }
}
