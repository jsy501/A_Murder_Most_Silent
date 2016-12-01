package com.noname.mrch;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;

import java.util.Stack;

/**
 *  Initialises and manages rooms
 *  Responsible for swapping rendering stage
 *  Dependent on all of the other managers
 */

public class RoomManager {
    private static RoomManager Instance = null;

    private Array<Room> roomArray; // all of the rooms EXCLUDING locked room
    private Room lockedRoom;
    private Stack<Stage> stageStack = new Stack<>();

    RoomManager(){
        roomArray = AssetLoader.getInstance().totalRoomArray;
        stageStack.push(roomArray.first()); //first room should always be hub

        // pick random room to be locked, excluding hub
        lockedRoom = roomArray.get(MathUtils.random(1,roomArray.size-1));
        lockedRoom.setLocked(true);

        roomArray.removeValue(lockedRoom, false);
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

    public void setBackground(AssetLoader assetLoader){
        for (Room room : roomArray){
            Texture background = assetLoader.manager.get("asset/graphics/" + String.valueOf(room.getId()) + ".png");
            room.setBackground(background);
        }
    }

    public Room getLockedRoom(){
        return lockedRoom;
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
