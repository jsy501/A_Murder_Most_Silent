package com.noname.mrch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.noname.mrch.GameWorld;
import com.noname.mrch.helper.AssetLoader;
import com.noname.mrch.libgdx.GameInputAdapter;

public class MRCH extends ApplicationAdapter {
	public AssetLoader assetLoader;
	public CharacterManager characterManager;
	public ItemManager itemManager;
	public ClueManager clueManager;
	public RoomManager roomManager;

	SpriteBatch batch;
	GameInputAdapter eventHandling = new GameInputAdapter();
	@Override
	public void create () {
		assetLoader = AssetLoader.getInstance();
		characterManager = CharacterManager.getInstance();
		itemManager = ItemManager.getInstance();
		clueManager = ClueManager.getInstance();
		roomManager = RoomManager.getInstance();

		System.out.println("Character list: " + characterManager.getCharacterArray());
		System.out.println("Murderer: " + characterManager.getMurderer());
		System.out.println("Victim: " + characterManager.getVictim());
		System.out.println("Item list: " + itemManager.getItemArray());
		System.out.println("Motive Clue: " + clueManager.getMotiveClue());
		System.out.println("Weapon Clue: " + clueManager.getWeaponClue());
		System.out.println("Appearance Clues: " + clueManager.getAppearanceClue());
		System.out.println("Room list: " + roomManager.getRoomArray());

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
