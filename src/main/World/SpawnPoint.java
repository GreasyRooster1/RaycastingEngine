package main.World;

import main.Main;
import main.Util.Point;

public class SpawnPoint {
    public Point position;
    public void render(){
        Main.app.fill(1,0,1);
        Main.app.noStroke();
        Main.app.ellipse(position.x,position.y,5,5);
    }
}
