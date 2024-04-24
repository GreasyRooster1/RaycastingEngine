package main.Editor.Components.Panels;

import main.Editor.Components.Buttons.TextureButton;
import main.Editor.Components.TextButton;
import main.Editor.UIPanel;

public class TexturePanel extends UIPanel {
    public TexturePanel() {
    }

    @Override
    public TextButton createAccessorButton() {
        TextButton button = new TextButton(10,410,80,80);
        button.text = "Textures";
        return button;
    }

    public void createComponents(){
        addComponent(new TextureButton(10,10,90,90));
    }
}
