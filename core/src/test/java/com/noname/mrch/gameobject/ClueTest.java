package com.noname.mrch.gameobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.noname.mrch.gameobject.ClueType.Motive;
import static org.junit.Assert.*;

import com.badlogic.gdx.utils.Array;

/**
 * Clue Unit test
 */
public class ClueTest {
    Clue clue;
    Array<Integer> characterRelated;
    GameCharacter succesfulTarget;
    GameCharacter failingTarget;

    @Before
    public void setUp() throws Exception {
        succesfulTarget = new GameCharacter(0,"test name", Personality.Aggressive, false, false, false, "hello test", "negative response test");
        failingTarget = new GameCharacter(5, "test name", Personality.Aggressive, false, false, false, "hello", "negative test response");

        characterRelated = new Array<>();
        characterRelated.add(0);
        characterRelated.add(1);

        clue = new Clue(0,"test clue", "test description", Motive, characterRelated, "test response");
    }

    @After
    public void tearDown() throws Exception {
        clue = null;
        succesfulTarget = null;
        failingTarget = null;
        characterRelated = null;
    }

    @Test
    public void getIdTest() throws Exception {
        assertEquals("failure - test id not returned", 0, clue.getId());
    }

    @Test
    public void getDescriptionTest() throws Exception {
        assertEquals("failure - test description not returned", "test description", clue.getDescription());
    }

    @Test
    public void getRelatedCharIdTest() throws Exception {
        assertEquals("failure - test character id not returned", characterRelated, clue.getRelatedCharId());
    }

    @Test
    public void getClueTypeTest() throws Exception {
        assertEquals("failure - test clue type not returned", Motive, clue.getClueType());
    }

    @Test
    public void getResponseTest() throws Exception {
        assertEquals("failure - test response not returned", "test response", clue.getResponse());
    }

    @Test
    public void checkIdTest() throws Exception {
        assertEquals("failure - returned boolean was not correct", true, clue.checkId(succesfulTarget));
        assertEquals("failure - returned boolean was not correct", false, clue.checkId(failingTarget));
    }
}