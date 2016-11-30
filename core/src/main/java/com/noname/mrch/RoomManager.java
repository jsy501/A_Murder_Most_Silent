package com.noname.mrch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;

import java.util.Stack;

/**
 *  Initialises and manages rooms
 *  Responsible for swapping rendering stage
 */

public class RoomManager {
    private static RoomManager Instance = null;

    private Array<Room> roomArray;
    private Stack<Stage> stageStack = new Stack<>();

    RoomManager(){
        roomArray = AssetLoader.getInstance().totalRoomArray;
        stageStack.push(roomArray.first()); //first room should always be hub
    }

    static void createInstance(){
        if (Instance == null) {
            Instance = new RoomManager();
        }
    }

    public static RoomManager getInstance(){
        if (Instance != null) {
            return Instance;
        }
        else{
            createInstance();
            return Instance;
        }
    }

    public void setBackground(){

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
