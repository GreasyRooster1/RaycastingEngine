package main.AssetLoader;

import main.Main;
import processing.core.PImage;


import java.io.File;
import java.util.Objects;

import static processing.core.PApplet.*;

public class AssetLoader {
    static String assetsPath = new File(System.getProperty("user.dir")).getAbsolutePath()+"\\";
    public static Asset[] assets = {};

    public static void registerImages(){
        register("assets\\Walls\\brick.jpg","walls.brick");
        register("assets\\Walls\\cobble.jpg","walls.cobble");
    }

    public static void register(String path,String name){
        println(assetsPath+path);
        PImage image = Main.app.loadImage(assetsPath+path);
        assets = (Asset[]) append(assets,new Asset(image,name));
    }

    public static PImage getImage(String name) {
        for (Asset asset : assets) {
            if(Objects.equals(asset.id, name)){
                return asset.image;
            }
        }
        return null;
    }
}
