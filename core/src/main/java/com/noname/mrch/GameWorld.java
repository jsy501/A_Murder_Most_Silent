package com.noname.mrch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.mrch.gameobject.Room;

/**
 * Initialises and holds all the game objects
 */

public class GameWorld {
    private Stage currentRoom;

    public GameWorld() {
        currentRoom = RoomManager.getInstance().getCurrentStage();
    }

    public Stage getCurrentRoom(){
        return currentRoom;
    }
}
