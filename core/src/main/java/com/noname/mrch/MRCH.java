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
import com.noname.mrch.libgdx.GameInputAdapter;

public class MRCH extends ApplicationAdapter {
	SpriteBatch batch;
	GameInputAdapter eventHandling = new GameInputAdapter();
	@Override
	public void create () {
		batch = new SpriteBatch();

		GameWorld gameWorld = new GameWorld();
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
