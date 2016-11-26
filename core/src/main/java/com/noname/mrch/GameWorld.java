package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameObject.Person;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.Item;
import com.noname.mrch.helper.Randomiser;

/**
 * Holds all the game objects
 */

public class GameWorld {
    private Array<Item> itemList = new Array<>();
    private Array<Clue> clueList = new Array<>();
    private Array<Person> personList = new Array<>();

    private Item key;

    public GameWorld(){
        Randomiser randomiser = new Randomiser();

        Json json = new Json();
        Array<Item> totalItemList = json.fromJson(Array.class, Item.class, Gdx.files.local("items.json"));
        randomiser.generateRandomArray(totalItemList, 5);

        Array<Clue> totalClueList = json.fromJson(Array.class, Clue.class, Gdx.files.local("clues.json"));

        Array<Clue> totalCharacterList = json.fromJson(Array.class, Person.class, Gdx.files.local("persons.json"));

        key = new Item(500, "It's a key", true);


    }
}
