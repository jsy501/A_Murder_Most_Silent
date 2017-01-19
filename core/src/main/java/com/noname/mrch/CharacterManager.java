package com.noname.mrch;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.AssetLoader;
import com.noname.mrch.helper.InitUtil;

/**
 *  Initialises and manages characters
 *  Must be initialised earlier than clue manager and item manager
 */

public class CharacterManager {
    /**
     * The CHARACTER_COUNT value defines the number of
     * game characters that will be added into the game.
     */
    public static final int CHARACTER_COUNT = 6;


    private GameCharacter murderer;
    private GameCharacter victim;

    private Array<GameCharacter> characterArray = new Array<>(); //character array EXCLUDING victim


    /**
     *  Pick n number of character from the total character roster randomly and
     *  choose murderer and victim randomly
     */
    public CharacterManager(AssetLoader assetLoader) {
        characterArray = InitUtil.generateRandomArray(assetLoader.totalCharacterArray, CHARACTER_COUNT);

        init();
        setImage(assetLoader);
    }

    /**
     * Constructor used only for testing
     * @param jsonFile json file for character import
     */

    public CharacterManager(FileHandle jsonFile){
        Json json = new Json();

        Array<GameCharacter> totalCharacterArray = json.fromJson(Array.class, GameCharacter.class, jsonFile);
        characterArray = InitUtil.generateRandomArray(totalCharacterArray, CHARACTER_COUNT);

        init();
    }

    private void init(){
        victim = characterArray.pop();
        victim.setVictim(true);

        murderer = characterArray.random();
        murderer.setMurderer(true);
    }

    private void setImage(AssetLoader assetLoader){
        TextureAtlas textureAtlas = assetLoader.manager.get(assetLoader.characterTexturePath);
        for (GameCharacter character : characterArray){
            TextureRegion image = new TextureRegion(textureAtlas.findRegion(String.valueOf(character.getId())));
            character.setImage(image);
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
