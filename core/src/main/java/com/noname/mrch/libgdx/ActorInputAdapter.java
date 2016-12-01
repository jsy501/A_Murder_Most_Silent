package com.noname.mrch.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.noname.mrch.Interactions;
import com.noname.mrch.gameobject.*;

/**
 * handles input processing functions called when an event
 * of the specified type is detected.
 */
public class ActorInputAdapter extends InputListener{
    private Actor actor;
    private String selectedOption;
    private Room room;
    private String toGui;
    private Interactions interactions = Interactions.getInstance();
    private int questioningStyle;

    public ActorInputAdapter(Actor actor){
        this.actor = actor;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button){
        if (this.actor instanceof Clue) {
            //todo gui displays some info
            NoteBook.getInstance().addClue((Clue) actor);
            room =(Room) actor.getStage();
            room.removeClue((Clue) actor);

            System.out.println("Clue clicked");

        } else if (actor instanceof Item) {
            //todo gui displays some info
            NoteBook.getInstance().addItem((Item) actor);
            room = (Room) actor.getStage();
            room.removeItem((Item) actor);

            System.out.println("Item clicked");

        } else if (this.actor instanceof GameCharacter) {
            //todo gui displays options
//            selectedOption = "question"; //this is set by the gui option
//            questioningStyle = 1; // provided by gui option
//
//            if (selectedOption == "question") {
//                toGui = interactions.question(questioningStyle, (GameCharacter) actor);
//            } else if (selectedOption == "give") {
//                //todo add code to run the stuff
//            } else if (selectedOption == "accuse") {
//                //todo add code to run the stuff
//            } else if (selectedOption == "ignore") {
//                toGui = interactions.ignore();
//            }

            System.out.println("Character clicked");

        } else{
            System.out.println("Background clicked");
        }
    }
}

