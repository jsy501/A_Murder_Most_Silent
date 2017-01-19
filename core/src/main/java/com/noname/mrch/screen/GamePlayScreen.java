package com.noname.mrch.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.gui.Gui;
import com.noname.mrch.GameWorld;
import com.noname.mrch.MurderSilentGame;

/**
 * Contains the functions needed to render the game play screen
 */

public class GamePlayScreen implements Screen {
    /**
     * game contains parameters and functions needed for setting the screen size and rendering
     * gameWorld contains an object containing all in game objects and logic to be referenced
     * gui contains gui items to be used and displayed
     */
    private MurderSilentGame game;

    private GameWorld gameWorld;
    private Gui gui;

    /**
     * Constructor for initialising the game once the player chooses a detective to play as
     * @param game game object containing functions required for rendering the screen
     * @param selectedPlayer selectedPlayer is the detective the player selects before the game starts
     */
    public GamePlayScreen(MurderSilentGame game, Player selectedPlayer){
        this.game = game;
        gameWorld = new GameWorld(selectedPlayer, this.game.assetLoader);
        gui = new Gui(this.game.assetLoader, gameWorld);

        gameWorld.setGui(gui);
    }

    /**
     * Initialises and sets the input multiplexer to the stage for the current room
     */
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

        gui.getStage().act();
        gui.getStage().draw();

    }

    /**
     * allows the player to resize the game screen
     * @param width new width of the screen
     * @param height new height of the screen
     */
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
