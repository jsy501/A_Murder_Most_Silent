package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;

import java.util.Stack;

public class RoomManager {
    private static RoomManager Instance = new RoomManager();

    private Array<Room> roomArray;
    private Stack<Stage> stageStack = new Stack<>();

    RoomManager(){
        roomArray = AssetLoader.getInstance().totalRoomClue;
        stageStack.push(roomArray.first());
    }
    public static RoomManager getInstance(){
        return Instance;
    }

    public Array<Room> getRoomArray(){
        return roomArray;
    }

    public void setCurrentStage(Stage stage){
        stageStack.pop();
        stageStack.push(stage);
    }

    public Stage getCurrentStage(){
        return stageStack.peek();
    }
}
