package main.Texture;

import main.Main;
import main.World.Wall;

public abstract class Texture {
    public int id;
    public boolean isTransparent = false;

    public int getColor(Wall wall, float uv_x, float uv_y){
        //just uses whatever one the texture defines,
        //still kinda janky tho
        return getColorFromUV(uv_x, uv_y)+getColorFromWall(wall,uv_x, uv_y);
    }

    public int getColorFromUV(float uv_x, float uv_y){
        return 0;
    }
    public int getColorFromWall(Wall wall, float uv_x, float uv_y){
        return 0;
    }

    public void renderTexture(float x,float y,float w,float h,int res){
        for (int i = 0; i < w; i+=res) {
            for (int j = 0; j < h; j+=res) {
                Main.app.fill(getColorFromUV(i/w,j/h));
                Main.app.rect(x+i,y+j,res,res);
            }
        }
    }
}
