package com.noname.mrch;

import com.badlogic.gdx.math.MathUtils;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.NoteBook;
import com.noname.mrch.gameobject.QuestioningStyle;

import java.util.concurrent.ThreadLocalRandom;



/**
 * Interactions class handles methods for interacting between
 * the player and the characters.
 */
public class Interactions {

    public static String question(QuestioningStyle questionStyle, GameCharacter target) {
        if (target.isAccused()) {
            return "Go Away";
        } else {
            int diff = Math.abs(questionStyle.getValue() - target.getPersonality().getValue());
            int chanceOfSuccess = MathUtils.random(1, 9)+(diff);
            if (chanceOfSuccess >= 5){
                Clue clue = target.getClueList().random();
                target.removeClue(clue);

                return clue.getResponse();
            } else {
                return target.getQuestionFailResponse();
            }
        }
    }

    public static String give(Item item, GameCharacter target){
        if (target.isAccused()){
            return "Go Away!";
        } else {
            //todo change some stuff m
        }
        return null;
    }

    public static String accuse(GameCharacter target, Clue murderMotive, Clue murderWeapon, Clue clueOne, Clue clueTwo, Clue clueThree){
        if (murderMotive.checkId(target) && murderWeapon.checkId(target) && clueOne.checkId(target) && clueTwo.checkId(target) && clueThree.checkId(target)){
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
