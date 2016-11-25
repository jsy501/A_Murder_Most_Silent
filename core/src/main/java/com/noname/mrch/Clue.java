package main.java.com.noname.mrch;

import com.badlogic.gdx.utils.Array;

public class Clue {
    private int id;
    private String name;
    private String description;
    private Array<Integer> relatedCharId = new Array<Integer>();

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
