package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noname.mrch.GameWorld;

public class NoteBookWindow extends GuiWindow {
    public NoteBookWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("NOTEBOOK", skin, gui, gameWorld);

        button("OK", true);

        setMovable(false);
        setModal(true);
    }

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
}
