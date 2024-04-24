package main.World.Editor.Components.Panels;

import main.World.Editor.Components.Buttons.TextureButton;
import main.World.Editor.UIPanel;

import java.awt.*;

public class TexturePanel extends UIPanel {
    public TexturePanel() {
    }

    public void createComponents(){
        addComponent(new TextureButton(10,10,90,90));
    }
}
