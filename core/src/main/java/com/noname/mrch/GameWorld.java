package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameObject.Person;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.Item;
import com.noname.mrch.helper.Randomiser;

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
        Randomiser randomiser = new Randomiser();
        Json json = new Json();
        Array<Person> totalCharacterList = json.fromJson(Array.class, Person.class, Gdx.files.local("persons.json"));
        personList = randomiser.generateRandomArray(totalCharacterList, PERSON_COUNT);
    }

    private void initItemList() {
        Json json = new Json();
        Array<Item> totalItemList = json.fromJson(Array.class, Item.class, Gdx.files.local("items.json"));

        for (int i = 0; i < personList.size; i++){
            int index = personList.get(i).getId() - 300;
            itemList.add(totalItemList.get(index));
        }

        key = new Item(500, "It's a key", true);
    }

    private void initClueList() {
        Randomiser randomiser = new Randomiser();
        Json json = new Json();
        Array<Clue> totalClueList = json.fromJson(Array.class, Clue.class, Gdx.files.local("clues.json"));
        clueList = randomiser.filterClues(totalClueList, personList);
    }
}
