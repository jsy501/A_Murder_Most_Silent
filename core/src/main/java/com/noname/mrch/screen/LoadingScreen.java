package com.noname.mrch.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.mrch.MurderSilentGame;
import com.noname.mrch.helper.AssetLoader;

public class LoadingScreen implements Screen{
    private MurderSilentGame game;

    private SpriteBatch batch;
    private BitmapFont loadingFont;
    private AssetLoader assetLoader;

    public LoadingScreen(final MurderSilentGame game){
        this.game = game;
        this.assetLoader = game.assetLoader;

        batch = new SpriteBatch();
        loadingFont = new BitmapFont();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (assetLoader.manager.update()){
            game.setScreen(new MainMenuScreen(game));
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
