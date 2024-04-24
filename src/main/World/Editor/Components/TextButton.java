package main.World.Editor.Components;

import main.Main;
import main.World.Editor.UIComponent;
import processing.core.PConstants;

public class TextButton extends UIComponent {
    public String text = "";

    public TextButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }

    @Override
    public void render() {
        Main.app.fill(0.75f);
        Main.app.rect(x,y,w,h,15);
        Main.app.fill(0f);
        Main.app.textSize(20);
        Main.app.textAlign(PConstants.CENTER);
        Main.app.text(text,x,y,w,h);
    }
}
