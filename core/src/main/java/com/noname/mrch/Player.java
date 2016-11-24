package com.noname.mrch;

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

    void setLocation(Room room){
        // Will be called everytime the player moves to a new
        // location such as changing rooms
        location = room;
    }

    String getName(){
        return name;
    }

    int getPersonality(){
        return personality;
    }

    Room getLocation(){
        return location;
    }
}
