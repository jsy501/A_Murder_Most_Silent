package com.noname.mrch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.helper.AssetLoader;

/**
 *  Initialises and manages items
 *  Dependent on CharacterManager
 */

public class ItemManager {
    private Array<Item> itemArray = new Array<>(); //all of the items EXCLUDING key
    private Item key;

    /**
     * Filter out unnecessary items from a total item array and assign every item to every character
     */

    public ItemManager (AssetLoader assetLoader, CharacterManager characterManager) {
        Array<Item> totalItemList = assetLoader.totalItemClueArray;
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();

        TextureAtlas textureAtlas = assetLoader.manager.get(assetLoader.itemTexturePath);

        //pick items that are relevant to the characters in the game
        for (int i = 0; i < characterArray.size; i++) {
            int index = characterArray.get(i).getId() - GameCharacter.ID_OFFSET;
            Item item = totalItemList.get(index);
            itemArray.add(item);
            item.setImage(textureAtlas.findRegion(String.valueOf(item.getId())));
        }

        key = new Item(500, "key", "It's a key", true); // the final item to be given
    }

    public Array<Item> getItemArray(){
        return itemArray;
    }

    public Item getKey(){
        return key;
    }
}
