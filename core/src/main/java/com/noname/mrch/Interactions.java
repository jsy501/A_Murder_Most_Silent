package com.noname.mrch;

import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.NoteBook;

import java.util.concurrent.ThreadLocalRandom;



/**
 * Interactions class handles methods for interacting between
 * the player and the characters.
 */
public class Interactions {
    private static Interactions INSTANCE = new Interactions();


    public static Interactions getInstance(){
        return INSTANCE;
    }

    public static String question(int questionStyle, GameCharacter target) {
        if (target.isAccused() == true) {
            return "Go Away";
        } else {
            int diff = questionStyle - target.getPersonality().getValue();
            int chanceOfSuccess = ThreadLocalRandom.current().nextInt(0, 9 + 1)+(diff);
            if (chanceOfSuccess >= 5){
                Clue clue = target.getClueList().random();
                target.removeClue(clue);
                NoteBook.getInstance().addClue(clue);
                return clue.getResponse();
            } else {
                return target.getResponse();
            }
        }
    }

    public static String give(Item item, GameCharacter target){
        if (target.isAccused() == true){
            return "Go Away!";
        } else {
            //todo change some stuff m
        }
        return null;
    }

    public static String accuse(GameCharacter target, Clue murderMotive, Clue murderWeapon, Clue clueOne, Clue clueTwo, Clue clueThree){
        if (murderMotive.checkId(target) == true && murderWeapon.checkId(target) == true && clueOne.checkId(target) == true && clueTwo.checkId(target) == true && clueThree.checkId(target) == true){
            return "You have accused the correct person congratulations you win!"; //placeholder
        } else {
            target.setAccused(true);
            return "you have accused the wrong person"; //placeholder
        }
    }

    public static String ignore(){
        return "Good bye"; // characters goodbye code
    }
}
