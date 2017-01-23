package com.noname.mrch.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.gameobject.Room;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class AssetLoader {
    public AssetManager manager;

    private String skinPath;
    private String titleFontPath;
    private String playerJsonPath;
    private String characterJsonPath;
    private String itemJsonPath;
    private String motiveClueJsonPath;
    private String weaponClueJsonPath;
    private String appearanceClueJsonPath;
    private String roomJsonPath;

    public Array<Player> totalPlayerArray;
    public Array<GameCharacter> totalCharacterArray;
    public Array<Item> totalItemClueArray;
    public Array<Clue> totalMotiveClueArray;
    public Array<Clue> totalWeaponClueArray;
    public Array<Clue> totalAppearanceClueArray;
    public Array<Room> totalRoomArray;

    public String playerTexturePath;
    public String characterTexturePath;
    public String roomTexturePath;
    public String itemTexturePath;
    public String clueTexturePath;

    public Skin skin;
    public BitmapFont titleFont;

    public AssetLoader() {
        manager = new AssetManager();

        getAssetPath();

        skin = new Skin(new FileHandle(skinPath));
        loadFonts();
        loadJson();
        loadTexture();
    }

    private void getAssetPath(){
        FileHandle file = new FileHandle("assets.properties");
        Properties properties = new Properties();
        try {
            properties.load(file.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

        skinPath = properties.getProperty("skinPath");

        titleFontPath = properties.getProperty("titleFontPath");

        playerJsonPath = properties.getProperty("playerJsonPath");
        characterJsonPath = properties.getProperty("characterJsonPath");
        itemJsonPath = properties.getProperty("itemJsonPath");
        motiveClueJsonPath = properties.getProperty("motiveClueJsonPath");
        weaponClueJsonPath = properties.getProperty("weaponClueJsonPath");
        appearanceClueJsonPath = properties.getProperty("appearanceClueJsonPath");
        roomJsonPath = properties.getProperty("roomJsonPath");

        playerTexturePath = properties.getProperty("playerTexturePath");
        characterTexturePath = properties.getProperty("characterTexturePath");
        roomTexturePath = properties.getProperty("roomTexturePath");
        itemTexturePath = properties.getProperty("itemTexturePath");
        clueTexturePath = properties.getProperty("clueTexturePath");
    }

    private void loadFonts(){
        //freetype font generation for title
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle(titleFontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 100;
        parameter.spaceX = 10;

        titleFont = generator.generateFont(parameter);
        generator.dispose();
    }

    private void loadJson(){
        Json json = new Json();

        totalPlayerArray = json.fromJson(Array.class, Player.class, new FileHandle(playerJsonPath));
        totalCharacterArray = json.fromJson(Array.class, GameCharacter.class, new FileHandle(characterJsonPath));
        totalItemClueArray = json.fromJson(Array.class, Item.class, new FileHandle(itemJsonPath));
        totalMotiveClueArray = json.fromJson(Array.class, Clue.class, new FileHandle(motiveClueJsonPath));
        totalWeaponClueArray = json.fromJson(Array.class, Clue.class, new FileHandle(weaponClueJsonPath));
        totalAppearanceClueArray = json.fromJson(Array.class, Clue.class, new FileHandle(appearanceClueJsonPath));
        totalRoomArray = json.fromJson(Array.class, Room.class, new FileHandle(roomJsonPath));
    }

    private void loadTexture(){
        //load player texture
        manager.load(playerTexturePath, TextureAtlas.class);

        //load room texture
        manager.load(roomTexturePath, TextureAtlas.class);

        //load character texture
        manager.load(characterTexturePath, TextureAtlas.class);

        //load item texture
        manager.load(itemTexturePath, TextureAtlas.class);

        //load clue texture
        manager.load(clueTexturePath, TextureAtlas.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
