package com.noname.mrch.libgdx;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.noname.mrch.gameobject.*;

/**
 * handles input processing functions called when an event
 * of the specified type is detected.
 */
public class ActorInputAdapter extends InputListener{
    GameActor actor;
    String selectedOption;
    public ActorInputAdapter(GameActor actor){
        this.actor = actor;
    }
    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button){
        if (this.actor instanceof Clue) {
            //todo gui displays some info
            NoteBook.getInstance().addClue((Clue)this.actor);
            (Room) actor.getStage().removeClue((Clue)this.actor);
            this.actor.remove();
        } else if (this.actor instanceof Item) {
            //todo gui displays some info
            NoteBook.getInstance().addItem((Item)this.actor);
            (Room) actor.getStage().removeItem((Item)this.actor);
            this.actor.remove();
        } else if (this.actor instanceof GameCharacter) {
            //todo gui displays options
            if (selectedOption == "question") {
                
            }

        }
    }
}

