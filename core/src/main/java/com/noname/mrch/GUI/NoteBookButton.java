package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Gui button that opens notebook window.
 */

class NoteBookButton extends GuiButton {
    NoteBookButton(Skin skin, Gui gui) {
        super(skin, "notebook", gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.getNotebookWindow().refresh();
                gui.getNotebookWindow().show(gui.getStage());
            }
        });
    }
}