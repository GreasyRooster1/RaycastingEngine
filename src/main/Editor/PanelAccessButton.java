package main.Editor;

import main.Editor.Components.TextButton;

public class PanelAccessButton extends TextButton {
    public UIPanel panel=null;
    public TextButton button=null;
    public PanelAccessButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }

    public void syncWithPanel(UIPanel p){
        panel = p;
        button = panel.createAccessorButton();
        text = button.text;
        x = button.x;
        y = button.y;
        w = button.w;
        h = button.h;
    }

    @Override
    public void onPress() {
        boolean wasActive = panel.active;
        MapEditor.closePanels();
        if(!wasActive) {
            panel.open();
        }
    }
}
