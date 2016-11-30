package com.noname.mrch.helper;

import com.badlogic.gdx.assets.AssetManager;
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
    public static AssetLoader Instance = new AssetLoader();

    public AssetManager manager;

    public Array<GameCharacter> totalCharacterArray;
    public Array<Item> totalItemClueArray;
    public Array<Clue> totalMotiveClueArray;
    public Array<Clue> totalWeaponClueArray;
    public Array<Clue> totalAppearanceClueArray;
    public Array<Room> totalRoomArray;

    private AssetLoader() {
        manager = new AssetManager();

        //load json
        Json json = new Json();
        totalCharacterArray = json.fromJson(Array.class, GameCharacter.class, new FileHandle("asset/json/characters.json"));
        totalItemClueArray = json.fromJson(Array.class, Item.class, new FileHandle("asset/json/items.json"));
        totalMotiveClueArray = json.fromJson(Array.class, Clue.class, new FileHandle("asset/json/motive_clues.json"));
        totalWeaponClueArray = json.fromJson(Array.class, Clue.class, new FileHandle("asset/json/weapon_clues.json"));
        totalAppearanceClueArray = json.fromJson(Array.class, Clue.class, new FileHandle("asset/json/appearance_clues.json"));
        totalRoomArray = json.fromJson(Array.class, Room.class, new FileHandle("asset/json/rooms.json"));

    }

    public static AssetLoader getInstance(){
        return Instance;
    }

    public void loadTexture(){
        //load room texture
        manager.load("asset/graphics/400.png", Texture.class);
        manager.load("asset/graphics/401.png", Texture.class);
        manager.load("asset/graphics/402.png", Texture.class);
        manager.load("asset/graphics/403.png", Texture.class);
        manager.load("asset/graphics/404.png", Texture.class);
        manager.load("asset/graphics/405.png", Texture.class);

        //load character texture
        manager.load("asset/graphics/character_pack.pack", TextureAtlas.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
