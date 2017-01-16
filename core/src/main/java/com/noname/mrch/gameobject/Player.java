package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * This class holds data about the player
 */
public class Player {
    private String name;
    private Array<QuestioningStyle> questionSet;

    public Player(String name){
        this.name = name;
        questionSet = new Array<>();
        questionSet.addAll(QuestioningStyle.Q1, QuestioningStyle.Q2, QuestioningStyle.Q3);
    }

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
