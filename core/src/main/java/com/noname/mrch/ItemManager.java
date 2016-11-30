package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.helper.AssetLoader;

/**
 *  Initialises and manages items
 *  Dependent on CharacterManager
 */

public class ItemManager {
    private static ItemManager Instance = null;

    private Array<Item> itemArray = new Array<>();
    private Item key;

    /**
     * Filter out unnecessary items from a total item array and assign every item to every character
     */

    private ItemManager () {
        AssetLoader assetLoader = AssetLoader.getInstance();

        Array<Item> totalItemList = assetLoader.totalItemClue;
        Array<GameCharacter> characterArray = CharacterManager.getInstance().getCharacterArray();

        //pick items that are relevant to the characters in the game
        for (int i = 0; i < characterArray.size; i++) {
            int index = characterArray.get(i).getId() - GameCharacter.ID_OFFSET;
            itemArray.add(totalItemList.get(index));
        }

        key = new Item(500, "key", "It's a key", true); // the final item to be given
        itemArray.add(key);

        for (int i = 0; i < characterArray.size; i++) {
            //assign every item but the first one to every character
            //the first item is to be found in a room
            characterArray.get(i).addItem(itemArray.get(i+1));

            //create links between items
            itemArray.get(i).setReturnItem(itemArray.get(i+1));
        }
    }

    static void createInstance(){
        if (Instance == null) {
            Instance = new ItemManager();
        }
    }

    public static ItemManager getInstance(){
        if (Instance != null) {
            return Instance;
        }
        else{
            createInstance();
            return Instance;
        }
    }

    public Array<Item> getItemArray(){
        return itemArray;
    }

    public Item getKey(){
        return key;
    }
}
