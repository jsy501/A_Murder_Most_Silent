package com.noname.mrch.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.noname.mrch.GUI.GUIWidgetGroup;
import com.noname.mrch.GUI.NoteBookButton;
import com.noname.mrch.GameWorld;
import com.noname.mrch.MRCH;

public class GamePlayScreen implements Screen {
    private MRCH game;

    private GameWorld gameWorld;

    private GUIWidgetGroup gui;

    public GamePlayScreen(Game game){
        this.game = (MRCH) game;
        gameWorld = new GameWorld(this.game.assetLoader);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameWorld.getCurrentRoom().getCurrentStage());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameWorld.getCurrentRoom().getCurrentStage().act();
        gameWorld.getCurrentRoom().getCurrentStage().draw();
//        gui.getGuiStage().act();
//        gui.getGuiStage().draw();
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
