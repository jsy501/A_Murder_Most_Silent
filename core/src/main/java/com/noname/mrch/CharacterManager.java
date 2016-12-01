package com.noname.mrch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.AssetLoader;
import com.noname.mrch.helper.InitUtil;

/**
 *  Initialises and manages characters
 *  Must be initialised first among the other managers
 */

public class CharacterManager {
    private GameCharacter murderer;
    private GameCharacter victim;

    private Array<GameCharacter> characterArray = new Array<>(); //character array EXCLUDING victim


    /**
     *  Pick n number of character from the total character roster randomly and
     *  choose murderer and victim randomly
     */
    public CharacterManager(AssetLoader assetLoader) {

        characterArray = InitUtil.generateRandomArray(assetLoader.totalCharacterArray, GameCharacter.CHARACTER_COUNT);
        victim = characterArray.pop();
        victim.setVictim(true);

        murderer = characterArray.first();
        murderer.setMurderer(true);

        setImage(assetLoader);
    }

    private void setImage(AssetLoader assetLoader){
        TextureAtlas textureAtlas = assetLoader.manager.get("asset/graphics/character_pack.pack");
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
