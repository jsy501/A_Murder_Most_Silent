package com.noname.mrch;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.helper.AssetLoader;

/**
 *  Initialises and manages items
 *  Dependent on CharacterManager
 */

public class ItemManager {
    private static ItemManager Instance = null;

    private Array<Item> itemArray = new Array<>(); //all of the items EXCLUDING key
    private Item key;

    /**
     * Filter out unnecessary items from a total item array and assign every item to every character
     */

    private ItemManager () {
        AssetLoader assetLoader = AssetLoader.getInstance();

        Array<Item> totalItemList = assetLoader.totalItemClueArray;
        Array<GameCharacter> characterArray = CharacterManager.getInstance().getCharacterArray();

        //pick items that are relevant to the characters in the game
        for (int i = 0; i < characterArray.size; i++) {
            int index = characterArray.get(i).getId() - GameCharacter.ID_OFFSET;
            itemArray.add(totalItemList.get(index));
        }

        key = new Item(500, "key", "It's a key", true); // the final item to be given
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
