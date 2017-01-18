package com.noname.mrch.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.noname.mrch.MurderSilentGame;
import com.noname.mrch.helper.AssetLoader;

/**
 * contains the functions needed to render the loading screen
 */

public class LoadingScreen implements Screen{
    /**
     * game contains parameters and functions needed for setting the screen size and rendering
     * batch contains image sprites to be rendered
     * loadingFont contains the font for the loading text
     * assetLoader contains a pointer to the game asset loader
     */
    private MurderSilentGame game;

    private SpriteBatch batch;
    private BitmapFont loadingFont;
    private AssetLoader assetLoader;
    /**
     * constructor for initialising the loading screen
     * @param game game object containing functions required for rendering the screen
     */
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
