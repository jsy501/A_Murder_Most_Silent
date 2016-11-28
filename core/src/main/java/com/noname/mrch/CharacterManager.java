package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.InitUtil;


public class CharacterManager {
    private static CharacterManager Instance = new CharacterManager();
    private GameCharacter murderer;
    private GameCharacter victim;

    private Array<GameCharacter> characterArray = new Array<>();

    Json json = new Json();
    Array<GameCharacter> totalCharacterList = json.fromJson(Array.class, GameCharacter.class, Gdx.files.local("persons.json"));

    characterArray = InitUtil.generateRandomArray(totalCharacterList, CHARACTER_COUNT);
    
    murderer = characterArrayt.pop();
    murderer.setMurderer(true);

    public static CharacterManager getInstance(){
        return Instance;
    }
    public GameCharacter getMurder (){
        return murderer

    }
}
