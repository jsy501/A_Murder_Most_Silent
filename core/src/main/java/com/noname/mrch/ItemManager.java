package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;

/**
 *  Initialises and manages items
 */

public class ItemManager {
    private static ItemManager Instance = new ItemManager();
    private Array<Item> itemList = new Array<>();
    private Item key;

    ItemManager () {
        Json json = new Json();
        Array<Item> totalItemList = json.fromJson(Array.class, Item.class, Gdx.files.local("items.json"));
        Array<GameCharacter> characterArray = CharacterManager.getInstance().getCharacterArray();

        //pick items that are relevant to the characters in the game
        for (int i = 0; i < characterArray.size; i++) {
            int index = characterArray.get(i).getId() - GameCharacter.ID_OFFSET;
            itemList.add(totalItemList.get(index));

            if (i > 0) {
                characterArray.get(i - 1).addItem(itemList.peek());
            }
        }

        key = new Item(500, "key", "It's a key", true); // the final item to be given
        characterArray.get(characterArray.size - 1).addItem(key);

        itemList.add(key);

        //generate item links
        for (int i = 0; i < itemList.size - 1; i++) {
            itemList.get(i).setReturnItem(itemList.get(i + 1));
        }

    }


    public static  ItemManager getInstance(){
        return Instance;
    }
    public Item getKey(){
        return key;
    }
}
