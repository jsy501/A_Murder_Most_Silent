package com.noname.mrch;

import com.noname.mrch.gameObject.Person;
import com.noname.mrch.gameObject.Item;

/**
 * Interactions class handles methods for interacting between
 * the player and the characters.
 */
public class Interactions {
    private static Interactions INSTANCE = new Interactions();

    public static Interactions getInstance(){
        return INSTANCE;
    }

    public static String question(int questionStyle, Person target) {
        if (target.isFalseAccused() == true) {
            return "Go Away";
        } else {
            int diff = Math.abs(questionStyle - target.getPersonality());
            // todo once characters and dialogue finalized get do some math
        }
        return null;
    }

    public static String give(Item item, Person target){
        if (target.isFalseAccused() == true){
            return "Go Away!";
        } else {
            //todo change some stuff make it work
        }
        return null;
    }

    public static String accuse(Person target){
        //todo add the accuse conditions
        return null;
    }

    public static String ignore(){
        return "Good bye"; // characters goodbye code
    }
}
