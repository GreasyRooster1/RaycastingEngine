package main.World.Editor.Components.Buttons;

import main.Main;
import main.World.Editor.Components.TextButton;
import main.World.Editor.MapEditor;
import main.World.Editor.UIComponent;
import processing.core.PConstants;

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
