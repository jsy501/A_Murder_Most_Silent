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
import com.noname.mrch.MurderSilentGame;
import com.noname.mrch.gameobject.GameActor;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;

public class Gui {
    public static GuiButton touchedActor = null;

    private GameWorld gameWorld;

    private Stage stage;
    private Table table;

    private GuiButton notebookButton;
    private GuiButton investigateButton;
    private GuiButton mapButton;

    private GuiWindow notebookWindow;
    private GuiWindow mapWindow;
    private GuiWindow infoWindow;

    private Table interactionBox;

    public Gui(AssetLoader assetLoader, final GameWorld gameWorld){
        this.gameWorld = gameWorld;
        Skin skin = assetLoader.skin;

        stage = new Stage(new ScreenViewport());
        table = new Table(skin);

        table.setFillParent(true);
        table.align(Align.bottom);


        //notebook UI init
        notebookWindow = new NoteBookWindow(skin, gameWorld);
        notebookButton = new NoteBookButton(skin);

        //investigate UI init
        investigateButton = new InvestigateButton(skin);

        //map UI init
        mapWindow = new MapWindow(skin, gameWorld);
        mapButton = new GuiButton(skin);

        //info window init
        infoWindow = new InfoWindow(skin, gameWorld);

        //interaction box init
        interactionBox = new Table(skin);
        interactionBox.background(skin.getDrawable("default-round"));


        stage.addActor(table);

        showBottomUI();
    }

    public void update(float delta){
        //input handle
        if (notebookButton.isTouched()){
            notebookWindow.show(stage);
        }
        else if (investigateButton.isTouched()){
            gameWorld.getCurrentRoom().switchCurrentStage();
        }
        else if (mapButton.isTouched()){
            mapWindow.show(stage);
        }
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
        table.add(interactionBox).size(MurderSilentGame.GAME_WIDTH, MurderSilentGame.GAME_HEIGHT / 3);
    }

    public Stage getStage(){
        return stage;
    }
}
