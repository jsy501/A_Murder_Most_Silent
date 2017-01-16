package com.noname.mrch.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.noname.mrch.gameobject.GameActor;
import com.noname.mrch.gameobject.JsonImport;

public class Slot extends Button {
    private GameActor gameActor;

    public Slot(GameActor gameActor, Gui gui, Skin skin) {
        super(skin);

        this.gameActor = gameActor;
        add(gameActor).row();
        add(gameActor.getName()).row();

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.displayInfo(null, ((JsonImport)gameActor).getDescription());
            }
        });
    }

    public GameActor getObject() {
        return gameActor;
    }
}
