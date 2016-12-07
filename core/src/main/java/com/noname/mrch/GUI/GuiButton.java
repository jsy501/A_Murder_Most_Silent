package com.noname.mrch.GUI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.noname.mrch.GameWorld;

public class GuiButton extends ImageButton {
    private boolean isTouched = false;

    public GuiButton(Skin skin) {
        super(skin);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gui.touchedActor = (GuiButton) actor;
                isTouched = true;
            }
        });
    }

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean touched) {
        isTouched = touched;
    }
}
