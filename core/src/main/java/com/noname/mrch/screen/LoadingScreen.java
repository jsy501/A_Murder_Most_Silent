package com.noname.mrch.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.mrch.CharacterManager;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.AssetLoader;

public class LoadingScreen implements Screen{
    private Game game;

    private SpriteBatch batch;
    private BitmapFont loadingFont;
    private AssetLoader assetLoader = AssetLoader.getInstance();

    public LoadingScreen(final Game game){
        this.game = game;

        batch = new SpriteBatch();
        loadingFont = new BitmapFont();
    }

    @Override
    public void show() {
        assetLoader.loadTexture();
    }

    @Override
    public void render(float delta) {
        if (assetLoader.manager.update()){
            game.setScreen(new GamePlayScreen(game));
        }

        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        loadingFont.draw(batch, "NOW LOADING...", 50, 50);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
