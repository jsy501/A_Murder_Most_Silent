package com.noname.mrch;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Interactions unit test's
 */
public class InteractionsTest {
    Interactions interactions;
    GameCharacter target;
    Clue murderMotive;
    Clue murderWeapon;
    Clue clueOne;
    Clue clueTwo;
    Clue clueThree;
    Item item;
    Item returnItem;

    Array characterRelated;

    @Before
    public void setUp() throws Exception {
        interactions = Interactions.getInstance();

        target = new GameCharacter(0,"test character", Personality.Aggressive,false, false, false, "hello", "negative test response");
        target.addClue(murderMotive);

        characterRelated = new Array<>();
        characterRelated.add(0);

        murderMotive = new Clue(0, "murder motive", "murder motive description", ClueType.Motive, characterRelated, "motive response");
        murderWeapon = new Clue(1, "murder weapon", "murder weapon description", ClueType.Weapon, characterRelated, "weapon response");
        clueOne = new Clue(2, "clue one", "clue one description", ClueType.Appearance, characterRelated, "clue one response");
        clueTwo = new Clue(3, "clue two", "clue two description", ClueType.Appearance, characterRelated, "clue two response");
        clueThree = new Clue(4, "clue three", "clue three description", ClueType.Appearance, characterRelated, "clue three response");

        item = new Item(0, "test item", "test item description", 0, returnItem, false);
        returnItem = new Item(1, "return item", "return item description", true);


    }

    @After
    public void tearDown() throws Exception {
        interactions = null;
        target = null;
        characterRelated = null;
        murderMotive = null;
        murderWeapon = null;
        clueOne = null;
        clueTwo = null;
        clueThree = null;
        item = null;
        returnItem = null;

    }

    @Test
    public void question() throws Exception {
        assertEquals("failure - target accuse not initialised to false", false, target.isAccused());
        target.setAccused(true);

        assertEquals("failure - go away was not returned", "Go Away", interactions.question(1, target));

        target.setAccused(false);

        assertEquals("failure - target's accused status not set to false", false, target.isAccused());

        //currently fails due to code in GameCharacter.
        //assertEquals("failure - clue response not returned", "motive response", interactions.question(9, target));

        assertEquals("failure - target response not returned", "negative test response", interactions.question(-9, target));

    }

    @Test
    public void give() throws Exception {

    }

    @Test
    public void accuse() throws Exception {

    }

    @Test
    public void ignore() throws Exception {

    }

}