package com.noname.mrch;

/**
 * This class holds data about the player
 */
public class Player {
    private String name;
    private int personality;
    private int location = 0;

    public Player(String playerName, int playerPersonality){
        // Constructor for initialising player
        name = playerName;
        personality = playerPersonality;
    }

    void setLocation(int newLocation){
        // Will be called everytime the player moves to a new
        // location such as changing rooms
        location = newLocation;
    }

    String getName(){
        return name;
    }

    int getPersonality(){
        return personality;
    }

    int getLocation(){
        return location;
    }
}
