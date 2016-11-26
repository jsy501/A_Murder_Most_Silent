package com.noname.mrch.gameObject;

import com.noname.mrch.gameObject.Room;

/**
 * This class holds data about the player
 */
public class Player {
    private String name;
    private int personality;
    private Room location;

    public Player(String playerName, int playerPersonality){
        // Constructor for initialising player
        name = playerName;
        personality = playerPersonality;
    }

    public void setLocation(Room room){
        // Will be called everytime the player moves to a new
        // location such as changing rooms
        location = room;
    }

    public String getName(){
        return name;
    }

    public int getPersonality(){
        return personality;
    }

    public Room getLocation(){
        return location;
    }

    @Override
    public String toString() {
        return name;
    }
}
