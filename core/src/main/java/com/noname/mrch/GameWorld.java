package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameObject.Person;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.Item;

/**
 * Holds all the game objects
 */

public class GameWorld {
    private Array<Item> totalItemList;
    private Array<Clue> totalClueList;
    private Array<Person> totalCharacterList;

    public GameWorld(){
        Json json = new Json();
        totalItemList = json.fromJson(Array.class, Item.class, Gdx.files.local("items.json"));

        totalClueList = json.fromJson(Array.class, Clue.class, Gdx.files.local("clues.json"));

        totalCharacterList = json.fromJson(Array.class, Person.class, Gdx.files.local("characters.json"));
    }
}
