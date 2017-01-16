package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gameobject.Clue;

public class AccuseWindow extends GuiWindow {
    private final float WIDTH = 1500;
    private final float HEIGHT = 800;
    private final float SLOT_WIDTH = 150;
    private final float SLOT_GAP_HORIZONTAL = 100;
    private final float SLOT_GAP_VERTICAL = SLOT_GAP_HORIZONTAL * 0.25f;
    private final int COLUMN_COUNT = 3;

    private Table sourceClueTable;
    private Table targetClueTable;

    private Array<Clue> sourceClueArray;
    private Array<Clue> targetClueArray;

    private DragAndDrop dragAndDrop;

    public AccuseWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("ACCUSE", skin, gui, gameWorld);
        debugAll();

        sourceClueArray = new Array<>();
        targetClueArray = new Array<>();

        sourceClueTable = new Table(skin);
        sourceClueTable.align(Align.topLeft);
        sourceClueTable.setBackground(skin.getDrawable("default-round"));
        targetClueTable = new Table(skin);
        targetClueTable.align(Align.topLeft);
        targetClueTable.setBackground(skin.getDrawable("default-round"));
        targetClueTable.setTouchable(Touchable.enabled);

        getContentTable().add(sourceClueTable).width(Value.percentWidth(0.45f, this)).height(Value.percentHeight(0.9f, this));
        getContentTable().add(targetClueTable).width(Value.percentWidth(0.45f, this)).height(Value.percentHeight(0.9f, this));

        dragAndDrop = new DragAndDrop();

        button("cancel", false);
        button("OK", true);
    }

    void refresh(){
        sourceClueTable.clear();
        targetClueTable.clear();
        dragAndDrop.clear();

        sourceClueTable.add("NOTEBOOK").row();
        for (int i = 0; i < sourceClueArray.size; i++){
            Slot slot = new Slot(sourceClueArray.get(i), gui, getSkin());
            dragAndDrop.addSource(new SlotSource(slot, this));

            if ((i+1) % COLUMN_COUNT == 0){
                sourceClueTable.add(slot).width(SLOT_WIDTH).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).row();
            }
            else {
                sourceClueTable.add(slot).width(SLOT_WIDTH).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).padRight(SLOT_GAP_HORIZONTAL);
            }
        }

        targetClueTable.add("DRAG HERE TO PRESENT").row();
        for (int i = 0; i < targetClueArray.size; i++){
            Slot slot = new Slot(targetClueArray.get(i), gui, getSkin());

            if ((i+1) % COLUMN_COUNT == 0){
                targetClueTable.add(slot).width(SLOT_WIDTH).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).row();
            }
            else {
                targetClueTable.add(slot).width(SLOT_WIDTH).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).padRight(SLOT_GAP_HORIZONTAL);
            }
        }

        dragAndDrop.addTarget(new SlotTarget(targetClueTable, this));
    }

    /**
     * called every time accuse is called in order to reset
     */
    void initialize(){
        sourceClueArray.clear();
        sourceClueArray.addAll(gameWorld.getNotebook().getClueList());

        targetClueArray.clear();
        refresh();
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            gui.getInteractionBox().accuse();
        }
        else if (object.equals(false)){
            hide();
        }
    }

    public Array<Clue> getSourceClueArray() {
        return sourceClueArray;
    }

    public Array<Clue> getTargetClueArray() {
        return targetClueArray;
    }

    @Override
    public float getPrefWidth() {
        return WIDTH;
    }

    @Override
    public float getPrefHeight() {
        return HEIGHT;
    }
}
