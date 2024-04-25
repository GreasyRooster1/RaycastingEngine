package main.Editor.File;

import main.Texture.TextureRegistry;
import main.World.Wall;
import main.World.WallTypes.Door;
import main.World.World;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static main.World.World.walls;
import static processing.core.PApplet.*;

public class Loader {
    public static void load(String filename){
        JSONObject json = loadJSONObject(new File(filename));

        JSONObject spawnPoint = json.getJSONObject("spawnPoint");
        World.spawnPoint.position.x = spawnPoint.getFloat("x");
        World.spawnPoint.position.y = spawnPoint.getFloat("y");

        JSONArray world = json.getJSONArray("world");
        for (int i=0;i<world.size();i++){
            JSONObject jsonWall =  world.getJSONObject(i);
            Door wall = new Door(jsonWall.getFloat("x1"),jsonWall.getFloat("y1"),jsonWall.getFloat("x2"),jsonWall.getFloat("y2"));
            wall.texture = TextureRegistry.get(jsonWall.getInt("textureId"));
            wall.height = jsonWall.getFloat("height");
            walls = (Wall[]) append(walls,wall);
        }
    }
    public static void save(String filename){
        JSONObject json = new JSONObject();
        JSONArray jsonWalls = new JSONArray();
        for(Wall wall: walls){
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

        JSONObject spawnPoint = new JSONObject();
        spawnPoint.put("x",World.spawnPoint.position.x);
        spawnPoint.put("y",World.spawnPoint.position.y);
        json.put("spawnPoint",spawnPoint);

        writeFile(filename,json.toString());
    }

    public static void writeFile(String filename,String text){
        try {
            PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8);
            writer.println(text);
            writer.close();
        }catch(Exception e){
            println("could not write to the file");
        }
    }

    public static String readFile(String filename){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filename));
        }catch (Exception e){
            println("could not read from the file");
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            println("could not read from the file");
            return "";
        }
    }
}
