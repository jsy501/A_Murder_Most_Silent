package com.noname.mrch.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Button that toggles stages in room
 */

class InvestigateButton extends GuiButton {
    InvestigateButton(Skin skin, Gui gui) {
        super(skin, "investigate", gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameWorld.switchRoomView();

                //set input processor as the switched stage
                Gdx.input.setInputProcessor(new InputMultiplexer(getStage(), gameWorld.getCurrentRoom().getCurrentStage()));
            }
        });
    }
}
