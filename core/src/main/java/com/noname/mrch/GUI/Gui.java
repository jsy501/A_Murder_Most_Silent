package com.noname.mrch.GUI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.mrch.GameWorld;
import com.noname.mrch.MRCH;
import com.noname.mrch.gameobject.GameActor;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;

public class Gui {
    private GameWorld gameWorld;

    private Stage stage;
    private Table table;

    private Skin skin;

    private ImageButton notebookButton;
    private ImageButton investigateButton;
    private ImageButton mapButton;

    private Dialog notebookWindow;
    private Dialog mapWindow;

    private Dialog infoWindow;

    private Table interactionBox;

    private boolean isTouched = false;

    public Gui(AssetLoader assetLoader, final GameWorld gameWorld){
        this.gameWorld = gameWorld;

        skin = assetLoader.skin;

        stage = new Stage(new ScreenViewport());
        table = new Table(skin);

        table.setFillParent(true);
        table.align(Align.bottom);


        //notebook button
        notebookWindow = new Dialog("NOTEBOOK", skin){
            @Override
            public float getPrefWidth() {
                return 1500;
            }

            @Override
            public float getPrefHeight() {
                return 700;
            }

            @Override
            protected void result(Object object) {
                if (object.equals(true)){
                    hide();
                }
            }
        };
        notebookWindow.button("OK", true);

        notebookWindow.setMovable(false);
        notebookWindow.setModal(true);

        notebookButton = new ImageButton(skin);
        notebookButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isTouched = true;
                notebookWindow.show(stage);
            }
        });

        //investigate button
        investigateButton = new ImageButton(skin);
        investigateButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isTouched = true;
                gameWorld.getCurrentRoom().switchCurrentStage();
            }
        });

        //map button
        mapWindow = new Dialog("MAP", skin){
            @Override
            protected void result(Object object) {
                if (object.equals(true)){
                    hide();
                }
                else if (object.equals(0)){
                    gameWorld.moveRoom(400);
                }
                else if (object.equals(1)){
                    gameWorld.moveRoom(401);
                }
                else if (object.equals(2)){
                    gameWorld.moveRoom(402);
                }
                else if (object.equals(3)){
                    gameWorld.moveRoom(403);
                }
                else if (object.equals(4)){
                    gameWorld.moveRoom(404);
                }
                else{
                    gameWorld.moveRoom(405);
                }
            }
        };

        mapWindow.button("HUB", 0);
        mapWindow.button("ISLAND", 1);
        mapWindow.button("LECTURE HALL", 2);
        mapWindow.button("SEMINAR ROOM", 3);
        mapWindow.button("STUDY ROOM", 4);
        mapWindow.button("TUTOR ROOM", 5);

        mapWindow.getButtonTable().row().colspan(Room.ROOM_COUNT);
        mapWindow.button("CANCEL", true);

        mapWindow.getTitleLabel().setAlignment(Align.center);
        mapWindow.setMovable(false);
        mapWindow.setModal(true);

        mapButton = new ImageButton(skin);
        mapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isTouched = true;
                mapWindow.show(stage);
            }
        });

        showBottomUI();

        stage.addActor(table);


        infoWindow = new Dialog("NEW INFO!", skin){
            @Override
            protected void result(Object object) {
                if (object.equals(true)){
                    hide();
                }
            }
        };
        infoWindow.button("OK", true);
        infoWindow.setMovable(false);
        infoWindow.setModal(true);


        interactionBox = new Table(skin);
        interactionBox.background(skin.getDrawable("default-round"));

    }

    public void displayInfo(GameActor actor, String info){
        infoWindow.getContentTable().clearChildren();
        infoWindow.getContentTable().add(actor);
        infoWindow.text(info);
        infoWindow.show(stage);
    }

    private void hideBottomUI(){
        notebookButton.remove();
        investigateButton.remove();
        mapButton.remove();
    }

    private void showBottomUI(){
        table.add(notebookButton);
        table.add(investigateButton).expandX();
        table.add(mapButton);
    }

    public void hideInteractionUI(){
        interactionBox.remove();
    }

    public void showInteractionUI(){
        hideBottomUI();
        table.add(interactionBox).size(MRCH.GAME_WIDTH, MRCH.GAME_HEIGHT / 3);
    }

    public Stage getStage(){
        return stage;
    }

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean touched) {
        isTouched = touched;
    }
}
