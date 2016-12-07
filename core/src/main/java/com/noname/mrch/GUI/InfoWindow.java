package com.noname.mrch.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noname.mrch.GameWorld;

public class InfoWindow extends GuiWindow {
    public InfoWindow(Skin skin, GameWorld gameWorld) {
        super("NEW INFO", skin, gameWorld);

        button("OK", true);
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }
}
