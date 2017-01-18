package com.noname.mrch.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.noname.mrch.MurderSilentGame;
import com.noname.mrch.helper.AssetLoader;
/**
 * contains the functions needed to render the main menu screen
 */
public class MainMenuScreen implements Screen {
    /**
     * game contains parameters and functions needed for setting the screen size and rendering
     * assetLoader contains a pointer to the asset loader
     *
     * stage contains the stage used to display menu buttons
     * table contains the sub table which is populated and used by ui elements
     */
    private MurderSilentGame game;
    private AssetLoader assetLoader;

    private Stage stage;
    private Table table;

    /**
     * Constructor that initialises the main menu
     * @param game game object containing functions required for rendering the screen
     */
    public MainMenuScreen(final MurderSilentGame game){
        this.game = game;
        assetLoader = game.assetLoader;

        stage = new Stage(new FitViewport(MurderSilentGame.GAME_WIDTH, MurderSilentGame.GAME_HEIGHT));
        table = new Table(assetLoader.skin);
        table.setFillParent(true);

//        stage.setDebugAll(true);
    }

    /**
     * populates the gui table with ui elements and sets the input processor to the stage
     */
    @Override
    public void show() {
        Label.LabelStyle style = new Label.LabelStyle(assetLoader.titleFont, Color.WHITE);
        Label title = new Label("A\nMURDER\nMOST\nSILENT", style);
        title.setAlignment(Align.center);

        table.add(title).padTop(80).padBottom(120).row();
        TextButton newGame = new TextButton("NEW GAME", assetLoader.skin);
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayerSelectionScreen(game));
            }
        });

        TextButton quit = new TextButton("QUIT", assetLoader.skin);
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        table.add(newGame).padBottom(10).row();
        table.add(quit).top().expandY();

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
