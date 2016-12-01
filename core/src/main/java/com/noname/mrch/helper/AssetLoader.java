package com.noname.mrch.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.Room;

import java.io.IOException;
import java.util.Properties;

public class AssetLoader {
    public AssetManager manager;

    public Array<GameCharacter> totalCharacterArray;
    public Array<Item> totalItemClueArray;
    public Array<Clue> totalMotiveClueArray;
    public Array<Clue> totalWeaponClueArray;
    public Array<Clue> totalAppearanceClueArray;
    public Array<Room> totalRoomArray;

    public String characterTexturePath;
    public String roomTexturePath;

    public AssetLoader() {
        manager = new AssetManager();

        FileHandle file = Gdx.files.local("assets.properties");
        Properties properties = new Properties();
        try {
            properties.load(file.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String characterJsonPath = properties.getProperty("characterJsonPath");
        String itemJsonPath = properties.getProperty("itemJsonPath");
        String motiveClueJsonPath = properties.getProperty("motiveClueJsonPath");
        String weaponClueJsonPath = properties.getProperty("weaponClueJsonPath");
        String appearanceClueJsonPath = properties.getProperty("appearanceClueJsonPath");
        String roomJsonPath = properties.getProperty("roomJsonPath");

        characterTexturePath = properties.getProperty("characterTexturePath");
        roomTexturePath = properties.getProperty("roomTexturePath");

        //load json
        Json json = new Json();
        totalCharacterArray = json.fromJson(Array.class, GameCharacter.class, new FileHandle(characterJsonPath));
        totalItemClueArray = json.fromJson(Array.class, Item.class, new FileHandle(itemJsonPath));
        totalMotiveClueArray = json.fromJson(Array.class, Clue.class, new FileHandle(motiveClueJsonPath));
        totalWeaponClueArray = json.fromJson(Array.class, Clue.class, new FileHandle(weaponClueJsonPath));
        totalAppearanceClueArray = json.fromJson(Array.class, Clue.class, new FileHandle(appearanceClueJsonPath));
        totalRoomArray = json.fromJson(Array.class, Room.class, new FileHandle(roomJsonPath));

        loadTexture();
    }

    public void loadTexture(){
        //load room texture
        manager.load(roomTexturePath, TextureAtlas.class);

        //load character texture
        manager.load(characterTexturePath, TextureAtlas.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
