package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.InitUtil;


public class CharacterManager {

    private final int CHARACTER_COUNT = 7;

    private static CharacterManager Instance = new CharacterManager();
    private GameCharacter murderer;
    private GameCharacter victim;

    private Array<GameCharacter> characterArray = new Array<>();

    CharacterManager() {
        Json json = new Json();

        Array<GameCharacter> totalCharacterList = json.fromJson(Array.class, GameCharacter.class, Gdx.files.local("persons.json"));
        characterArray = InitUtil.generateRandomArray(totalCharacterList, CHARACTER_COUNT);
        murderer = characterArray.pop();
        murderer.setMurderer(true);

        victim = characterArray.pop();
        victim.setVictim(true);

    }

    public static CharacterManager getInstance(){
        return Instance;
    }


    public GameCharacter getMurderer (){
        return murderer;
    }
    public GameCharacter getVictim(){
        return victim;
    }
    public Array<GameCharacter> getCharacterArray(){

        return characterArray;
    }
}
