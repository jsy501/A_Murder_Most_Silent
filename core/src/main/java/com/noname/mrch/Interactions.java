package com.noname.mrch;

import com.noname.mrch.gameObject.GameCharacter;
import com.noname.mrch.gameObject.Item;
import java.util.Random;

/**
 * Interactions class handles methods for interacting between
 * the player and the characters.
 */
public class Interactions {
    private static Interactions INSTANCE = new Interactions();
    private static Random random = new Random();

    public static Interactions getInstance(){
        return INSTANCE;
    }

    public static String question(int questionStyle, GameCharacter target) {
        if (target.isAccused() == true) {
            return "Go Away";
        } else {
            int diff = Math.abs(questionStyle - target.getPersonality().getValue());
            float chanceOfSuccess = ((random.nextFloat())+(diff/10));
            if (chanceOfSuccess <= 0.5){
                //return target.getClue?
            } else {
                return target.getResponse();
            }
        }
        return null;
    }

    public static String give(Item item, GameCharacter target){
        if (target.isAccused() == true){
            return "Go Away!";
        } else {
            //todo change some stuff make it work
        }
        return null;
    }

    public static String accuse(GameCharacter target){
        //todo add the accuse conditions
        return null;
    }

    public static String ignore(){
        return "Good bye"; // characters goodbye code
    }
}
