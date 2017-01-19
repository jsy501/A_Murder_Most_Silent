package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * A button that opens map window.
 */

class MapButton extends GuiButton{
    MapButton(Skin skin, Gui gui) {
        super(skin, "map", gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.getMapWindow().show(gui.getStage());
            }
        });
    }
}

