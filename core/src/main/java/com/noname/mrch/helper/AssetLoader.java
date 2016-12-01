package com.noname.mrch.helper;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.Room;

public class AssetLoader {
    private String jsonPath;
    private String graphicsPath;

    public AssetManager manager;

    public Array<GameCharacter> totalCharacterArray;
    public Array<Item> totalItemClueArray;
    public Array<Clue> totalMotiveClueArray;
    public Array<Clue> totalWeaponClueArray;
    public Array<Clue> totalAppearanceClueArray;
    public Array<Room> totalRoomArray;

    public AssetLoader() {
        manager = new AssetManager();
        FileHandle file = new FileHandle("assetDirectory.txt");

        //load json
        Json json = new Json();
        totalCharacterArray = json.fromJson(Array.class, GameCharacter.class, new FileHandle("asset/json/characters.json"));
        totalItemClueArray = json.fromJson(Array.class, Item.class, new FileHandle("asset/json/items.json"));
        totalMotiveClueArray = json.fromJson(Array.class, Clue.class, new FileHandle("asset/json/motive_clues.json"));
        totalWeaponClueArray = json.fromJson(Array.class, Clue.class, new FileHandle("asset/json/weapon_clues.json"));
        totalAppearanceClueArray = json.fromJson(Array.class, Clue.class, new FileHandle("asset/json/appearance_clues.json"));
        totalRoomArray = json.fromJson(Array.class, Room.class, new FileHandle("asset/json/rooms.json"));

        loadTexture();
    }

    public void loadTexture(){
        //load room texture
        manager.load("asset/graphics/room_pack.pack", TextureAtlas.class);

        //load character texture
        manager.load("asset/graphics/character_pack.pack", TextureAtlas.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
