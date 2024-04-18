package main.AssetLoader;

import processing.core.PImage;

public class Asset {
    public PImage image;
    public String id;

    public Asset(PImage image, String name) {
        this.image = image;
        this.id = name;
    }
}
