package com.noname.mrch;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;

/**
 * Initialises and holds all the game objects
 */

public class GameWorld {
    private Room currentRoom;

    private CharacterManager characterManager;
    private ItemManager itemManager;
    private ClueManager clueManager;
    private RoomManager roomManager;

    public GameWorld(AssetLoader assetLoader) {
        characterManager = new CharacterManager(assetLoader);
        itemManager = new ItemManager(assetLoader, characterManager);
        clueManager = new ClueManager(assetLoader, characterManager);
        roomManager = new RoomManager(assetLoader);

        RoomManager roomManager = new RoomManager(assetLoader);

        Array<Item> itemArray = itemManager.getItemArray();
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        Array<Room> roomArray = roomManager.getRoomArray();

        // clue distribution
        roomManager.getLockedRoom().addClue(clueManager.getMotiveClue());

        // item distribution
        itemArray.add(itemManager.getKey());
        for (int i = 0; i < characterManager.getCharacterArray().size; i++) {
            //assign every item but the first one to every character
            //the first item is to be found in a room
            characterManager.getCharacterArray().get(i).addItem(itemManager.getItemArray().get(i+1));

            //create links between items
            itemManager.getItemArray().get(i).setReturnItem(itemManager.getItemArray().get(i+1));
        }
        itemArray.removeValue(itemManager.getKey(), false);
        roomArray.random().addItem(itemArray.get(0));


        // character distribution
        characterArray.shuffle();
        for (int i = 0; i < characterArray.size; i++){
            roomArray.get(i%Room.ROOM_COUNT).addCharacter(characterArray.get(i));
        }


        System.out.println("Character list: " + characterManager.getCharacterArray());
        System.out.println("Murderer: " + characterManager.getMurderer());
        System.out.println("Victim: " + characterManager.getVictim());
        System.out.println("Item list: " + itemManager.getItemArray());
        System.out.println("MOTIVE Clue: " + clueManager.getMotiveClue());
        System.out.println("WEAPON Clue: " + clueManager.getWeaponClue());
        System.out.println("APPEARANCE Clues: " + clueManager.getAppearanceClue());
        System.out.println("Room list: " + roomManager.getRoomArray());


        currentRoom = roomManager.getCurrentRoom();
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }
}
