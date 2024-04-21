package main.World.Editor.Buttons;

import main.Main;
import main.World.Editor.MapEditor;
import main.World.Editor.UIComponent;
import processing.core.PConstants;

public class WallButton extends UIComponent {
    public WallButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }

    @Override
    public void render() {
        Main.app.fill(0.75f);
        Main.app.rect(x,y,w,h,15);
        Main.app.fill(0f);
        Main.app.textSize(20);
        Main.app.textAlign(PConstants.CENTER);
        Main.app.text("Add Wall",x+w/2,y+h/2);
    }

    @Override
    public void onPress() {
        MapEditor.resetAction();
        MapEditor.placingWall = true;
    }
}
