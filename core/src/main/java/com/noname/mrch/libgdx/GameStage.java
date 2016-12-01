package com.noname.mrch.libgdx;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.mrch.gameobject.Room;

/**
 * Created by User on 01/12/2016.
 */
public class GameStage extends Stage {
    private Room room;

    public GameStage() {
        super(new ScreenViewport());
    }
}
