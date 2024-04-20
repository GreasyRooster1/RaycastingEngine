package main.Texture.TextureTypes;

import main.Main;
import main.Texture.Texture;
import main.World.Wall;

public class PortalTexture extends Texture {
    @Override
    public int getColorFromWall(Wall wall, float uv_x, float uv_y) {
        if(uv_y < wall.bottomHeight/wall.height){
            return 0;
        }
        if(uv_y > (wall.height-wall.topHeight)/wall.height){
            return 0;
        }
        return Main.app.color(255,0,255);
    }
}
