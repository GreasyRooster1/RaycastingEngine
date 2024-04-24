package main.Editor.Components.Panels;

import main.Editor.Components.Buttons.TextureButtons.SingleTextureButton;
import main.Editor.Components.Buttons.TextureButtons.TextureButton;
import main.Editor.Components.TextButton;
import main.Editor.UIPanel;
import main.Texture.TextureRegistry;

import static processing.core.PApplet.floor;

public class TexturePanel extends UIPanel {
    public float buttonSize = 60;
    public float buttonMargin = 10;
    public TexturePanel() {
    }

    @Override
    public TextButton createAccessorButton() {
        TextButton button = new TextButton(10,410,80,80);
        button.text = "Textures";
        return button;
    }

    public void createComponents(){
        //create main texture button
        addComponent(new TextureButton(410,310,90,90));

        //create all the generated ones
        for(int i=0;i<TextureRegistry.textures.length;i++) {
            float buttonsPerRow = floor(500/(buttonSize+buttonMargin));
            float x = buttonMargin+((i%buttonsPerRow)*(buttonSize+buttonMargin));
            float y = floor(i/buttonsPerRow)*(buttonSize+buttonMargin);
            addComponent(new SingleTextureButton(x, y, buttonSize, buttonSize, i));
        }
    }
}
