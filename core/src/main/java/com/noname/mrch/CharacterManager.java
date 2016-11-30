package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.AssetLoader;
import com.noname.mrch.helper.InitUtil;

/**
 *  Initialises and manages characters
 *  Must be initialised first among the other managers
 */

public class CharacterManager {
    private final int CHARACTER_COUNT = 5;

    private static CharacterManager Instance = null;
    private GameCharacter murderer;
    private GameCharacter victim;

    private Array<GameCharacter> characterArray = new Array<>(); //character array excluding victim


    /**
     *  Pick n number of character from the total character roster randomly and
     *  choose murderer and victim randomly
     */
    private CharacterManager() {
        AssetLoader assetLoader = AssetLoader.getInstance();

        characterArray = InitUtil.generateRandomArray(assetLoader.totalCharacterList, CHARACTER_COUNT);
        victim = characterArray.pop();
        victim.setVictim(true);

        murderer = characterArray.random();
        murderer.setMurderer(true);
    }

    static void createInstance(){
        if (Instance == null) {
            Instance = new CharacterManager();
        }
    }

    public static CharacterManager getInstance(){
        if (Instance != null) {
            return Instance;
        }
        else{
            createInstance();
            return Instance;
        }
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
