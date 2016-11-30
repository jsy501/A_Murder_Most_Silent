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
    Clue failClue;
    Item item;
    Item returnItem;

    Array characterRelated;
    Array characterRelatedFail;

    @Before
    public void setUp() throws Exception {
        interactions = Interactions.getInstance();

        characterRelated = new Array<>();
        characterRelated.add(0);

        characterRelatedFail = new Array<>();
        characterRelatedFail.add(99);

        murderMotive = new Clue(0, "murder motive", "murder motive description", ClueType.Motive, characterRelated, "motive response");
        murderWeapon = new Clue(1, "murder weapon", "murder weapon description", ClueType.Weapon, characterRelated, "weapon response");
        clueOne = new Clue(2, "clue one", "clue one description", ClueType.Appearance, characterRelated, "clue one response");
        clueTwo = new Clue(3, "clue two", "clue two description", ClueType.Appearance, characterRelated, "clue two response");
        clueThree = new Clue(4, "clue three", "clue three description", ClueType.Appearance, characterRelated, "clue three response");
        failClue = new Clue(5, "clue four", "clue four description", ClueType.Appearance, characterRelatedFail, "clue four response");

        item = new Item(0, "test item", "test item description", 0, returnItem, false);
        returnItem = new Item(1, "return item", "return item description", true);

        target = new GameCharacter(0,"test character", Personality.Aggressive,false, false, false, "hello", "negative test response");
        target.addClue(murderMotive);

    }

    @After
    public void tearDown() throws Exception {
        interactions = null;
        target = null;
        characterRelated = null;
        characterRelatedFail = null;
        murderMotive = null;
        murderWeapon = null;
        clueOne = null;
        clueTwo = null;
        clueThree = null;
        item = null;
        returnItem = null;

    }

    @Test
    public void questionIfAlreadyAccusedTest() throws Exception {
        assertEquals("failure - target accuse not initialised to false", false, target.isAccused());
        target.setAccused(true);

        assertEquals("failure - go away was not returned", "Go Away", interactions.question(1, target));
    }

    @Test
    public void questionIfNotAlreadyAccusedTest() throws Exception {
//        currently fails due to code in GameCharacter.
        assertEquals("failure - clue response not returned", "motive response", interactions.question(9, target));

        assertEquals("failure - target response not returned", "negative test response", interactions.question(-9, target));

    }

    @Test
    public void giveTest() throws Exception {

    }

    @Test
    public void accuseFailTest() throws Exception {
        assertEquals("failure - returned statement is not correct", "you have accused the wrong person", interactions.accuse(target,murderMotive,murderWeapon,clueOne,clueTwo, failClue));
        assertTrue("failure - accused status was not set to true", target.isAccused());

    }

    @Test
    public void accuseSuccesTest() throws Exception {
        assertEquals("failure - returned statement is not correct", "You have accused the correct person congratulations you win!", interactions.accuse(target, murderMotive, murderWeapon, clueOne, clueTwo, clueThree));
    }

    @Test
    public void ignore() throws Exception {

    }

}