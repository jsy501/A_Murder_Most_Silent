package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.ClueType;
import com.noname.mrch.helper.InitUtil;

/**
 * Initialises and holds all the game objects
 */

public class GameWorld {
    private final int CHARACTER_COUNT = 7;

    private Array<Item> itemList = new Array<>();
    private Array<Clue> clueList = new Array<>();
    private Array<GameCharacter> characterArray = new Array<>();

    private GameCharacter murderer;
    private GameCharacter victim;

    private Clue motiveClue;

    private Item key;

    public GameWorld(){
        initCharacterList();
        initItemList();
        initClueList();

        System.out.println(characterArray);
        System.out.println("Murderer: " + murderer.toString());
        System.out.println("Victim: " + victim.toString());
        System.out.println("Motive: " + motiveClue.toString());
        System.out.println(itemList);
        System.out.println(clueList);
    }

    private void initCharacterList() {
        Json json = new Json();
        Array<GameCharacter> totalCharacterList = json.fromJson(Array.class, GameCharacter.class, Gdx.files.local("persons.json"));
        characterArray = InitUtil.generateRandomArray(totalCharacterList, CHARACTER_COUNT);

        murderer = characterArray.pop();
        murderer.setMurderer(true);

        victim = characterArray.pop();
        victim.setVictim(true);
    }

    private void initItemList() {
        Json json = new Json();
        Array<Item> totalItemList = json.fromJson(Array.class, Item.class, Gdx.files.local("items.json"));

        //pick items that are relevant to the characters in the game
        for (int i = 0; i < characterArray.size; i++){
            int index = characterArray.get(i).getId() - GameCharacter.ID_OFFSET;
            itemList.add(totalItemList.get(index));

            if (i>0) {
                characterArray.get(i - 1).addItem(itemList.peek());
            }
        }

        key = new Item(500, "key", "It's a key", true); // the final item to be given
        characterArray.get(characterArray.size-1).addItem(key);

        itemList.add(key);

        //generate item links
        for (int i = 0; i < itemList.size-1; i++){
            itemList.get(i).setReturnItem(itemList.get(i+1));
        }
    }

    private void initClueList() {

    }
}
