package com.noname.mrch.gameobject;

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
