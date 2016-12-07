package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * This class holds data about the player
 */
public class Player {
    private String name;
    private Room location;
    private Array<QuestioningStyle> questionSet;

    public Player(String playerName){
        // Constructor for initialising player
        name = playerName;
        questionSet = new Array<>(3);
    }

    public void setLocation(Room room){
        // Will be called everytime the player moves to a new
        // location such as changing rooms
        location = room;
    }

    public String getName(){
        return name;
    }

    public Room getLocation(){
        return location;
    }

    @Override
    public String toString() {
        return name;
    }
}
