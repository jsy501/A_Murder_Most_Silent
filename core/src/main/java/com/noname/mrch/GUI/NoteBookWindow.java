package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gameobject.GameActor;
import com.noname.mrch.gameobject.JsonImport;
import com.noname.mrch.gameobject.NoteBook;

public class NoteBookWindow extends GuiWindow {
    private final float COLUMN_COUNT = 4;
    private final float SLOT_WIDTH = 200;
    private final float SLOT_GAP_HORIZONTAL = 100;
    private final float SLOT_GAP_VERTICAL = SLOT_GAP_HORIZONTAL * 0.25f;
    private final float WINDOW_WIDTH = SLOT_WIDTH * COLUMN_COUNT + SLOT_GAP_HORIZONTAL * (COLUMN_COUNT - 1) * 1.1f;
    private final float WINDOW_HEIGHT = 700;

    public NoteBookWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("NOTEBOOK", skin, gui, gameWorld);

        button("OK", true);

        setMovable(false);
        setModal(true);
    }

    /**
     * called every time notebook is opened. Clears all the children and rebuild.
     */
    public void refresh(){
        NoteBook noteBook = gameWorld.getNotebook();
        getContentTable().clear();
        getContentTable().align(Align.topLeft);

        Array<JsonImport> entryArray = new Array<>();
        entryArray.addAll(noteBook.getClueList());
        entryArray.addAll(noteBook.getItemList());

        for (int i = 0; i < entryArray.size; i++){
            Table slot = new Table(getSkin());
            slot.add((GameActor)entryArray.get(i)).row();
            slot.add(entryArray.get(i).getName()).row();

            Label description = new Label(entryArray.get(i).getDescription(), getSkin());
            description.setAlignment(Align.center);
            description.setWrap(true);
            slot.add(description).width(SLOT_WIDTH);

            if ((i+1) % COLUMN_COUNT == 0){
                getContentTable().add(slot).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).row();
            }
            else{
                getContentTable().add(slot).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).padRight(SLOT_GAP_HORIZONTAL);
            }
        }
    }

    @Override
    public float getPrefWidth() {
        return WINDOW_WIDTH;
    }

    @Override
    public float getPrefHeight() {
        return WINDOW_HEIGHT;
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }
}
