package main.World.Editor.Components.Panels;

import main.World.Editor.Components.Buttons.TextureButton;
import main.World.Editor.Components.TextButton;
import main.World.Editor.UIComponent;
import main.World.Editor.UIPanel;

import java.awt.*;

public class TexturePanel extends UIPanel {
    public TexturePanel() {
    }

    @Override
    public TextButton createAccessorButton() {
        TextButton button = new TextButton(10,10,80,80);
        button.text = "Textures";
        return button;
    }

    public void createComponents(){
        addComponent(new TextureButton(10,10,90,90));
    }
}
