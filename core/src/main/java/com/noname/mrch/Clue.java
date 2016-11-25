package com.noname.mrch;

import com.badlogic.gdx.utils.Array;

public class Clue {
    private int id;
    private String name;
    private String description;
    private Array<Integer> relatedCharId = new Array<Integer>();
    private String response;

    public Clue(){

    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Array<Integer> getRelatedCharId() {
        return relatedCharId;
    }
}
