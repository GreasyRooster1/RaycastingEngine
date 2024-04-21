package main.World.Editor.File;

import main.Main;
import main.World.Wall;
import main.World.World;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.PrintWriter;

import static processing.core.PApplet.println;

public class Loader {
    public static void load(){

    }
    public static void save(){
        JSONObject json = new JSONObject();
        JSONArray jsonWalls = new JSONArray();
        for(Wall wall: Main.app.walls){
            JSONObject jsonWall = new JSONObject();
            jsonWall.put("x1",wall.x1);
            jsonWall.put("y1",wall.y1);
            jsonWall.put("x2",wall.x2);
            jsonWall.put("y2",wall.y2);
            jsonWall.put("height",wall.height);
            jsonWall.put("textureId",wall.texture.id);
            jsonWalls.append(jsonWall);
        }
        json.put("world",jsonWalls);

        writeFile("world.json",json.toString());
    }

    public static void writeFile(String filename,String text){
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println(text);
            writer.close();
        }catch(Exception e){
            println("could not write to the file");
        }
    }
}
