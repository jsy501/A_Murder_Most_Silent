package com.noname.mrch.gameObject;

/**
 *  Represents an item object
 */

public class Item {
    private int id;
    private String description;
    private int targetClueId;   //ID of a clue that it unlocks

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getTargetClueId() {
        return targetClueId;
    }

}
