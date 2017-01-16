package com.noname.mrch.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.noname.mrch.MurderSilentGame;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.helper.AssetLoader;

public class PlayerSelectionScreen implements Screen {
    private MurderSilentGame game;
    private AssetLoader assetLoader;

    private Stage stage;
    private Table table;

    public PlayerSelectionScreen(final MurderSilentGame game){
        this.game = game;
        assetLoader = game.assetLoader;

        stage = new Stage(new FitViewport(MurderSilentGame.GAME_WIDTH, MurderSilentGame.GAME_HEIGHT));
        table = new Table(assetLoader.skin);
        table.setFillParent(true);
    }

    @Override
    public void show() {
        table.add("CHOOSE YOUR DETECTIVE TO PLAY").padBottom(50).row();

        Table selectionTable = new Table(assetLoader.skin);
        Array<Player> playerRoster = assetLoader.totalPlayerArray;
        for (Player player: playerRoster) {
            TextButton button = new TextButton(player.getName(), assetLoader.skin);
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.setScreen(new GamePlayScreen(game, player));
                }
            });
            selectionTable.add(button).size(200, 500);
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
