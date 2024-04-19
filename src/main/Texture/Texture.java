package main.Texture;

import main.Main;

public abstract class Texture {
    public int id;
    public int getColor(float uv_x,float uv_y){
        return 0;
    }
    public void renderTexture(float x,float y,float w,float h,int res){
        for (int i = 0; i < w; i+=res) {
            for (int j = 0; j < h; j+=res) {
                Main.app.fill(getColor(i/w,j/h));
                Main.app.rect(x+i,y+j,res,res);
            }
        }
    }
}
