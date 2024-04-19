package main.World.Editor.Buttons;

import main.Main;
import main.World.Editor.UIComponent;

public class WallButton extends UIComponent {
    public WallButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }

    @Override
    public void render() {
        Main.app.fill(0.75f);
        Main.app.rect(x,y,w,h,15);
    }
}
