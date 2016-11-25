package com.noname.mrch;

import com.noname.mrch.gameObject.Character;

/**
 * Interactions class handles methods for interacting between
 * the player and the characters.
 */
public class Interactions {
    String question(int questionStyle, Character target) {
        if (target.isFalseAccused() == true) {
            return "Go Away";
        } else {
            int diff = Math.abs(questionStyle - target.getPersonality());
            // todo once characters and dialogue finalized get do some math
        }
        return null;
    }

    String give(Item item, Character target){
        if (target.isFalseAccused() == true){
            return "Go Away!";
        } else {
            //todo change some stuff make it work
        }
        return null;
    }

    String accuse(Character target){
        //todo add the accuse conditions
        return null;
    }

    String ignore(){
        return "Good bye"; // characters goodbye code
    }
}
