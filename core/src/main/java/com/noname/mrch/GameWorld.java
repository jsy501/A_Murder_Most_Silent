package com.noname.mrch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Room;

/**
 * Initialises and holds all the game objects
 */

public class GameWorld {
    private Stage currentRoom;

    private CharacterManager characterManager = CharacterManager.getInstance();
    private ItemManager itemManager = ItemManager.getInstance();
    private ClueManager clueManager = ClueManager.getInstance();
    private RoomManager roomManager = RoomManager.getInstance();

    public GameWorld() {
        for (GameCharacter character : characterManager.getCharacterArray()){

        }

        currentRoom = RoomManager.getInstance().getCurrentStage();
    }

    public Stage getCurrentRoom(){
        return currentRoom;
    }
}
