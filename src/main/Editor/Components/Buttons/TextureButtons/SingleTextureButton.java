package main.Editor.Components.Buttons.TextureButtons;

import main.Editor.MapEditor;
import main.Editor.UIComponent;
import main.Main;
import main.Texture.TextureRegistry;

public class SingleTextureButton extends UIComponent {
    public int textureId = 0;
    public SingleTextureButton(float _x, float _y, float _w, float _h) {
        super(_x, _y, _w, _h);
    }

    @Override
    public void render() {
        Main.app.fill(0.75f);
        Main.app.rect(x,y,w,h,15);
        TextureRegistry.get(textureId).renderTexture(x+15,y+15,w-30,h-30,1);
    }

    @Override
    public void onPress() {
        MapEditor.textureId = textureId;
    }
}
