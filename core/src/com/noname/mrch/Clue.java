package com.noname.mrch;

import com.badlogic.gdx.utils.Array;

public class Clue {
    private int id;
    private String description;
    private Array<Character> relateTo = new Array<Character>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Array<Character> getRelateTo() {
        return relateTo;
    }

    public void setRelateTo(Array<Character> relateTo) {
        this.relateTo = relateTo;
    }
}
