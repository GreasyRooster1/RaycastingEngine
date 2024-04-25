package main.Util;

import main.Main;

import static processing.core.PApplet.append;

public class WallColorHelper {
    public static int[] wallColors = {};

    public static int getWallColor(int id) {
        if(wallColors.length==0){
            wallColors = append(wallColors,Main.app.color(1,0,1));
            wallColors = append(wallColors,Main.app.color(1,1,0));
        }
        while(wallColors.length <= id){
            wallColors = append(wallColors,Main.app.randomColor());
        }
        return wallColors[id];
    }
}
