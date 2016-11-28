package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.Room;

import java.util.Stack;

public class RoomManager {
    private static RoomManager Instance = new RoomManager();

    private Array<Room> roomArray;
    private Stack<Stage> stageStack = new Stack<>();

    RoomManager(){
        Json json = new Json();
        roomArray = json.fromJson(Array.class, Room.class, Gdx.files.local("rooms.json"));
    }
    public static RoomManager getInstance(){
        return Instance;
    }

    public void setCurrentStage(Stage stage){
        stageStack.pop();
        stageStack.push(stage);
    }

    public Stage getCurrentStage(){
        return stageStack.peek();
    }
}
