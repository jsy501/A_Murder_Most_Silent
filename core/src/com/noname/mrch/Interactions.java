package com.noname.mrch;

/**
 * Interactions class handles methods for interacting between
 * the player and the characters.
 */
public class Interactions {
    String question(int questionStyle,Character target){
        if (target.getFalseAccused() == true){
            return "Go Away";
        }
        else {
            int diff = Math.abs(questionStyle - target.getPersonality());
            // todo once characters and dialogue finalized get do some math
        }
    }

    String give(Item item, Character target){
        if (target.getFalseAccused() == true){
            return "Go Away!";
        } else {
            //todo change some stuff make it work
        }
    }

    String accuse(Character target){
        if (target.getFalseAccused() == true){
            return "Go Away!";
        } else{
            //todo add the accuse conditions
        }

    }

    String ignore(){
        return "Good bye" // characters goodbye code
    }
}
