package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.noname.mrch.GameWorld;

public class GuiWindow extends Dialog {
    protected Gui gui;
    protected GameWorld gameWorld;

    public GuiWindow(String title, Skin skin, Gui gui, GameWorld gameWorld) {
        super(title, skin);

        this.gui = gui;
        this.gameWorld = gameWorld;

        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setModal(true);
    }
}
