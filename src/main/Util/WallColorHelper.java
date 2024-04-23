package main.Util;

import main.Main;

import static processing.core.PApplet.append;

public class WallColorHelper {
    public static int[] wallColors = {};
    public static int getWallColor(int id) {
        while(wallColors.length <= id){
            wallColors = append(wallColors,Main.app.randomColor());
        }
        return wallColors[id];
    }
}
