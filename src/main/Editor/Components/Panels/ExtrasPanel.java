package main.Editor.Components.Panels;

import main.Editor.Components.Buttons.SaveButton;
import main.Editor.Components.Buttons.SpawnPointButton;
import main.Editor.Components.Buttons.WallButton;
import main.Editor.Components.TextButton;
import main.Editor.UIPanel;
import main.World.Wall;

public class ExtrasPanel extends UIPanel {
    public TextButton createAccessorButton() {
        TextButton button = new TextButton(400,410,80,80);
        button.text = "More...";
        return button;
    }

    @Override
    public void createComponents() {
        addComponent(new SaveButton(410,310,90,90));
        addComponent(new WallButton(10,10,90,90));
        addComponent(new SpawnPointButton(100,10,90,90));
    }
}
