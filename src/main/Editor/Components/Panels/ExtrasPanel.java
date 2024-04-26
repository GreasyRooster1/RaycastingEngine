package main.Editor.Components.Panels;

import main.Editor.Components.Buttons.ActionButtons.ClearButton;
import main.Editor.Components.Buttons.ActionButtons.ReloadButton;
import main.Editor.Components.Buttons.ActionButtons.SaveButton;
import main.Editor.Components.Buttons.ToolButtons.SpawnPointButton;
import main.Editor.Components.Buttons.ToolButtons.DoorButton;
import main.Editor.Components.Buttons.ToolButtons.WallButton;
import main.Editor.Components.TextButton;
import main.Editor.UIPanel;

public class ExtrasPanel extends UIPanel {
    public TextButton createAccessorButton() {
        TextButton button = new TextButton(410,410,80,80);
        button.text = "More...";
        return button;
    }

    @Override
    public void createComponents() {
        addComponent(new SaveButton(410,310,80,80));
        addComponent(new ClearButton(320,310,80,80));
        addComponent(new ReloadButton(230,310,80,80));

        addComponent(new WallButton(10,10,80,80));
        addComponent(new SpawnPointButton(100,10,80,80));
        addComponent(new DoorButton(190,10,80,80));
    }
}
