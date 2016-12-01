package com.noname.mrch.GUI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

/**
 * This class holds the stage and table for
 * all ui elements if they are added to this they
 * will appear on screen.
 */
public class GUIWidgetGroup {
    private Stage guiStage;
    private Table table;

    public GUIWidgetGroup(Stage stage, Table table) {
        this.guiStage = stage;
        this.table = table;
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true); //enables debug lines
    }

    public void addWidget(WidgetGroup widget) {
        table.add(widget);
    }

    public Stage getGuiStage(){
        return guiStage;
    }
}
