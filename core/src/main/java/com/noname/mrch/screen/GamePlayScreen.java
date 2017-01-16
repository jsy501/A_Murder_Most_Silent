package com.noname.mrch.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.gui.Gui;
import com.noname.mrch.GameWorld;
import com.noname.mrch.MurderSilentGame;

public class GamePlayScreen implements Screen {
    private MurderSilentGame game;

    private GameWorld gameWorld;
    private Gui gui;

    public GamePlayScreen(MurderSilentGame game, Player selectedPlayer){
        this.game = game;
        gameWorld = new GameWorld(selectedPlayer, this.game.assetLoader);
        gui = new Gui(this.game.assetLoader, gameWorld);

        gameWorld.setGui(gui);
    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer(gui.getStage(), gameWorld.getCurrentRoom().getCurrentStage());
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameWorld.update(delta);
        gameWorld.getCurrentRoom().getCurrentStage().act();
        gameWorld.getCurrentRoom().getCurrentStage().draw();

        gui.update(delta);
        gui.getStage().act();
        gui.getStage().draw();

    }

    @Override
    public void resize(int width, int height) {
        gameWorld.resize(width, height);
        gui.getStage().getViewport().update(width, height, true);
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
        gameWorld.dispose();
    }
}
