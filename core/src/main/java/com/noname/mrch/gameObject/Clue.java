package com.noname.mrch.gameObject;

import com.badlogic.gdx.utils.Array;

/**
 *  Represents a clue object
 */

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
