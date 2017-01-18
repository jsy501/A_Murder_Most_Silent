package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * This class holds data about the player
 */
public class Player {
    /**
     * use of each variable:
     * name contains the name of the detective
     * questionSet contains the 3 questioning styles the chosen detective may use
     */
    private String name;
    private Array<QuestioningStyle> questionSet;

    /**
     * Constructor used for testing only
     */
    public Player(String name){
        this.name = name;
        questionSet = new Array<>();
        questionSet.addAll(QuestioningStyle.Q1, QuestioningStyle.Q2, QuestioningStyle.Q3);
    }

    /**
     * Non-argument constructor used for json import
     */
    public Player(){

    }

    public String getName(){
        return name;
    }

    public Array<QuestioningStyle> getQuestionSet() {
        return questionSet;
    }

    @Override
    public String toString() {
        return name;
    }
}
