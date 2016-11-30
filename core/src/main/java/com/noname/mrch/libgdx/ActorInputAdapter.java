package com.noname.mrch.libgdx;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.noname.mrch.Interactions;
import com.noname.mrch.gameobject.*;

/**
 * handles input processing functions called when an event
 * of the specified type is detected.
 */
public class ActorInputAdapter extends InputListener{
    private GameActor actor;
    private String selectedOption;
    private Room room;
    private String toGui;
    private Interactions interactions = Interactions.getInstance();
    private int questioningStyle;

    public ActorInputAdapter(GameActor actor){
        this.actor = actor;
    }
    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button){
        if (this.actor instanceof Clue) {
            //todo gui displays some info
            NoteBook.getInstance().addClue((Clue) actor);
            room =(Room) actor.getStage();
            room.removeClue((Clue) actor);
            actor.remove();
        } else if (actor instanceof Item) {
            //todo gui displays some info
            NoteBook.getInstance().addItem((Item) actor);
            room = (Room) actor.getStage();
            room.removeItem((Item) actor);
            actor.remove();
        } else if (this.actor instanceof GameCharacter) {
            //todo gui displays options
            selectedOption = "question"; //this is set by the gui option
            questioningStyle = 1; // provided by gui option
            if (selectedOption == "question") {
                toGui = interactions.question(questioningStyle, (GameCharacter) actor);
            } else if (selectedOption == "give") {
                //todo add code to run the stuff
            } else if (selectedOption == "accuse") {
                //todo add code to run the stuff
            } else if (selectedOption == "ignore") {
                toGui = interactions.ignore();
            }

        }
    }
}

