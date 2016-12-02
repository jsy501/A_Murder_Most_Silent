package com.noname.mrch;

import com.badlogic.gdx.math.MathUtils;
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

    public String question(int questionStyle, GameCharacter target) {
        if (target.isAccused() == true) {
            return "Go Away";
        } else {
            int diff = Math.abs(questionStyle - target.getPersonality().getValue());
            int chanceOfSuccess = MathUtils.random(1, 9)+(diff);
            if (chanceOfSuccess >= 5){
                Clue clue = target.getClueList().random();
                target.removeClue(clue);
//                NoteBook.getInstance().addClue(clue);
                return clue.getResponse();
            } else {
                return target.getResponse();
            }
        }
    }

    public String give(Item item, GameCharacter target){
        if (target.isAccused() == true){
            return "Go Away!";
        } else {
            //todo change some stuff m
        }
        return null;
    }

    public String accuse(GameCharacter target, Clue murderMotive, Clue murderWeapon, Clue clueOne, Clue clueTwo, Clue clueThree){
        if (murderMotive.checkId(target) == true && murderWeapon.checkId(target) == true && clueOne.checkId(target) == true && clueTwo.checkId(target) == true && clueThree.checkId(target) == true){
            return "You have accused the correct person congratulations you win!"; //placeholder
        } else {
            target.setAccused(true);
            return "you have accused the wrong person"; //placeholder
        }
    }

    public String ignore(){
        return "Good bye"; // characters goodbye code
    }
}
