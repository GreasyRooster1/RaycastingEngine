package main.AssetLoader;

import main.Main;
import processing.core.PImage;


import static processing.core.PApplet.append;

public class AssetLoader {
    public static Asset[] assets;
    public static void registerImages(){
        register("assets/Walls/brick.jpg","walls.brick");
    }
    public static void register(String path,String name){
        PImage image = Main.app.loadImage(path);
        assets = (Asset[]) append(assets,new Asset(image,name));
    }
}
