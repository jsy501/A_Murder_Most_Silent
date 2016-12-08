package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noname.mrch.GameWorld;

public class InfoWindow extends GuiWindow {
    public InfoWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("INFO", skin, gui, gameWorld);

        button("OK", true);
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }
}
