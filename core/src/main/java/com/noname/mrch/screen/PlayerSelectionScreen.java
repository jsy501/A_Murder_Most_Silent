package com.noname.mrch.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.noname.mrch.MurderSilentGame;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.helper.AssetLoader;

/**
 * contains the functions needed to render the player selection screen
 */
public class PlayerSelectionScreen implements Screen {
    /**
     * game contains parameters and functions needed for setting the screen size and rendering
     * assetLoader contains a pointer to the game asset loader
     * stage contains the stage used to display menu options
     * table contains the sub table which is populated and used by ui elements
     */
    private MurderSilentGame game;
    private AssetLoader assetLoader;

    private Stage stage;
    private Table table;

    /**
     * Constructor that initialises the player selection screen
     * @param game game object containing functions required for rendering the screen
     */
    public PlayerSelectionScreen(final MurderSilentGame game){
        this.game = game;
        assetLoader = game.assetLoader;

        stage = new Stage(new FitViewport(MurderSilentGame.GAME_WIDTH, MurderSilentGame.GAME_HEIGHT));
        table = new Table(assetLoader.skin);
        table.setFillParent(true);
    }

    /**
     * Adds buttons to the gui table to be rendered and sets the input processor to the correct stage
     */
    @Override
    public void show() {
        table.add("CHOOSE YOUR DETECTIVE TO PLAY").padBottom(50).row();

        Table selectionTable = new Table(assetLoader.skin);
        Array<Player> playerRoster = assetLoader.totalPlayerArray;
        TextureAtlas textureAtlas = assetLoader.manager.get(assetLoader.playerTexturePath);
        for (Player player: playerRoster) {
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = new TextureRegionDrawable(textureAtlas.findRegion(String.valueOf(player.getId())));
            ImageButton button = new ImageButton(style);
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.setScreen(new GamePlayScreen(game, player));
                }
            });
            selectionTable.add(button);
        }
        table.add(selectionTable);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
        stage.dispose();
    }
}
