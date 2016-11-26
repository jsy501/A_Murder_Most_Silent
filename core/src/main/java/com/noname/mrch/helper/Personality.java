package com.noname.mrch.helper;

public enum Personality {
    Friendly(0), Neutral(1), Aggressive(2);

    private int value;

    private Personality(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
