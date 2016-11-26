package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.noname.mrch.gameObject.Person;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.Item;
import com.noname.mrch.helper.InitUtil;

/**
 * Initialises and holds all the game objects
 */

public class GameWorld {
    private final int PERSON_COUNT = 3;

    private Array<Item> itemList = new Array<>();
    private Array<Clue> clueList = new Array<>();
    private Array<Person> personList = new Array<>();

    private Item key;

    public GameWorld(){
        initPersonList();
        initItemList();
        initClueList();
    }

    private void initPersonList() {
        Json json = new Json();
        Array<Person> totalCharacterList = json.fromJson(Array.class, Person.class, Gdx.files.local("persons.json"));
        personList = InitUtil.generateRandomArray(totalCharacterList, PERSON_COUNT);
    }

    private void initItemList() {
        Json json = new Json();
        Array<Item> totalItemList = json.fromJson(Array.class, Item.class, Gdx.files.local("items.json"));

        //pick items that are relevant to the characters in the game
        for (int i = 0; i < personList.size; i++){
            int index = personList.get(i).getId() - 300;
            itemList.add(totalItemList.get(index));

            if (i>0) {
                personList.get(i - 1).addItem(itemList.peek());
            }
        }

        key = new Item(500, "key", "It's a key", true); // the final item to be given
        personList.get(personList.size-1).addItem(key);

        itemList.add(key);

        //generate item links
        for (int i = 0; i < itemList.size-1; i++){
            itemList.get(i).setReturnItem(itemList.get(i+1));
        }
    }

    private void initClueList() {
        Json json = new Json();
        Array<Clue> totalClueList = json.fromJson(Array.class, Clue.class, Gdx.files.local("clues.json"));
        clueList = InitUtil.filterClues(totalClueList, personList);
    }
}
