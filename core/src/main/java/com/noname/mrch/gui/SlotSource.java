package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

/**
 * Source for Drag And Drag in accuse window.
 */

public class SlotSource extends Source{
    private AccuseWindow accuseWindow;

    private Actor sourceActor;
    private Payload payload;

    public SlotSource(Actor actor, AccuseWindow accuseWindow) {
        super(actor);

        this.accuseWindow = accuseWindow;
        sourceActor = actor;
        payload = new Payload();
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        payload.setObject(sourceActor);
        payload.setDragActor(sourceActor);
        return payload;
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        if (target == null) {
            accuseWindow.refresh();
        }
    }
}
