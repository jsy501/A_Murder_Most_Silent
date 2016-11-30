package com.noname.mrch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.mrch.helper.AssetLoader;
import com.noname.mrch.screen.GamePlayScreen;

public class MRCH extends Game {
	public static float GAME_HEIGHT;
	public static float GAME_WIDTH;

	public AssetLoader assetLoader;
	public CharacterManager characterManager;
	public ItemManager itemManager;
	public ClueManager clueManager;
	public RoomManager roomManager;

	@Override
	public void create () {
		GAME_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = Gdx.graphics.getWidth();

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

		this.setScreen(new GamePlayScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		assetLoader.dispose();
	}
}
