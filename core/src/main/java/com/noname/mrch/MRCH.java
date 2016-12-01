package com.noname.mrch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.AssetLoader;
import com.noname.mrch.screen.GamePlayScreen;
import com.noname.mrch.screen.LoadingScreen;

public class MRCH extends Game {
	public static float GAME_HEIGHT;
	public static float GAME_WIDTH;

	public AssetLoader assetLoader;

	@Override
	public void create () {
		GAME_HEIGHT = Gdx.graphics.getHeight();
		GAME_WIDTH = Gdx.graphics.getWidth();

		assetLoader = new AssetLoader();

		setScreen(new LoadingScreen(this));
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
