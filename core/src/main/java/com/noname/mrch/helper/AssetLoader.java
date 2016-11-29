package com.noname.mrch.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;

public class AssetLoader {
    public static AssetLoader Instance = new AssetLoader();

    private AssetManager manager;

    public Array<GameCharacter> totalCharacterList;
    public Array<Item> totalItemClue;
    public Array<Clue> totalMotiveClue;
    public Array<Clue> totalWeaponClue;
    public Array<Clue> totalAppearanceClue;

    private AssetLoader() {
        manager = new AssetManager();

        Json json = new Json();
        totalCharacterList = json.fromJson(Array.class, GameCharacter.class, new FileHandle("characters.json"));
        totalItemClue = json.fromJson(Array.class, Item.class, new FileHandle("items.json"));
        totalMotiveClue  = json.fromJson(Array.class, Clue.class, new FileHandle("motive_clues.json"));
        totalWeaponClue = json.fromJson(Array.class, Clue.class, new FileHandle("weapon_clues.json"));
        totalAppearanceClue = json.fromJson(Array.class, Clue.class, new FileHandle("appearance_clues.json"));
    }

    public static AssetLoader getInstance(){
        return Instance;
    }

    public void dispose(){
        manager.dispose();
    }
}
