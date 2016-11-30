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
        System.out.println("Character list: " + characterManager.getCharacterArray());
        System.out.println("Murderer: " + characterManager.getMurderer());
        System.out.println("Victim: " + characterManager.getVictim());
        System.out.println("Item list: " + itemManager.getItemArray());
        System.out.println("Motive Clue: " + clueManager.getMotiveClue());
        System.out.println("Weapon Clue: " + clueManager.getWeaponClue());
        System.out.println("Appearance Clues: " + clueManager.getAppearanceClue());
        System.out.println("Room list: " + roomManager.getRoomArray());


        currentRoom = roomManager.getCurrentStage();
    }

    public Stage getCurrentRoom(){
        return currentRoom;
    }
}
