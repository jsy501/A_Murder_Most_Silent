package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;
import com.noname.mrch.gameobject.Clue;

/**
 * Target for Drag And Drag in accuse window
 */

public class SlotTarget extends Target {
    private AccuseWindow accuseWindow;

    public SlotTarget(Actor actor, AccuseWindow accuseWindow) {
        super(actor);

        this.accuseWindow = accuseWindow;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer){
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        Clue sourceClue = (Clue) ((Slot)source.getActor()).getObject();

        accuseWindow.getSourceClueArray().removeValue(sourceClue, false);
        accuseWindow.getTargetClueArray().add(sourceClue);
        accuseWindow.refresh();
    }
}
