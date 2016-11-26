package com.noname.mrch.gameObject;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.helper.ClueType;

/**
 *  Represents a clue object
 */

public class Clue {
    public static final int ID_OFFSET = 100;

    private int id;
    private String name;
    private String description;
    private ClueType clueType;
    private Array<Integer> relatedCharId = new Array<Integer>();
    private String response;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Array<Integer> getRelatedCharId() {
        return relatedCharId;
    }

    public ClueType getClueType() {
        return clueType;
    }

    @Override
    public String toString() {
        return name;
    }
}
