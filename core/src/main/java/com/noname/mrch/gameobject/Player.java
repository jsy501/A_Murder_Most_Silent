package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * This class holds data about the player
 */
public class Player implements JsonImport{
    /**
     * The ID_OFFSET variable is used in order to differentiate between
     * types of objects independent of everything else the id offset
     * is added to the id of the object imported via json.
     */
    public static final int ID_OFFSET = 500;

    /**
     * use of each variable:
     * name contains the name of the detective
     * questionSet contains the 3 questioning styles the chosen detective may use
     */
    private int id;
    private String name;
    private String desctription;
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

    @Override
    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    @Override
    public String getDescription() {
        return desctription;
    }

    public Array<QuestioningStyle> getQuestionSet() {
        return questionSet;
    }

    @Override
    public String toString() {
        return name;
    }
}
