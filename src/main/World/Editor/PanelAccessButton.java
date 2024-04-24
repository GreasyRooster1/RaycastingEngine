package main.World.Editor;

import main.World.Editor.Components.TextButton;

import java.awt.*;

public class PanelAccessButton extends TextButton {
    public UIPanel panel=null;
    public TextButton button=null;
    public PanelAccessButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }

    public void syncWithTemplate(){
        button = panel.createAccessorButton();
        text = button.text;
        x = button.x;
        y = button.y;
        w = button.w;
        h = button.h;
    }

    @Override
    public void onPress() {
        MapEditor.closePanels();
        panel.open();
    }
}
