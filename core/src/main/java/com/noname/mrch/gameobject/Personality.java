package com.noname.mrch.gameobject;

/**
 * Used to classify the personality type of the target character
 * during interactions associated int values used for calculating
 * success chance during questioning
 */
public enum Personality {
    FRIENDLY(2), NEUTRAL(5), AGGRESSIVE(8);

    private int value;

    private Personality(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
