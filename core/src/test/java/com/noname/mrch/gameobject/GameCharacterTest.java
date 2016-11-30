package com.noname.mrch.gameobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GameCharacter Unit Tests
 */
public class GameCharacterTest {
    GameCharacter testCharacter;
    Clue testClue;
    Item testItem;

    @Before
    public void setUp() throws Exception {
        testCharacter = new GameCharacter(0 , "test name", Personality.Aggressive, false, false, false, "test greeting", "test negative response");

        testClue = new Clue();
        testItem = new Item();
    }

    @After
    public void tearDown() throws Exception {
        testCharacter = null;

        testClue = null;
        testItem = null;
    }

    @Test
    public void getId() throws Exception {
        assertEquals("failure - testCharacter did not initialise with correct id", 0, testCharacter.getId());
    }

    @Test
    public void getGreeting() throws Exception {
        assertEquals("failure - returned greeting was not correct", "test greeting", testCharacter.getGreeting());
    }

    @Test
    public void getResponse() throws Exception {
        assertEquals("failure - returned response was not correct", "test negative response", testCharacter.getResponse());
    }

    @Test
    public void getPersonality() throws Exception {
        assertEquals("failure - returned personality was not correct", Personality.Aggressive, testCharacter.getPersonality());
    }

    @Test
    public void isMurderer() throws Exception {
        assertFalse(testCharacter.isMurderer());
    }

    @Test
    public void setMurderer() throws Exception {
        assertFalse(testCharacter.isMurderer());

        testCharacter.setMurderer(true);

        assertTrue(testCharacter.isMurderer());
    }

    @Test
    public void isVictim() throws Exception {
        assertFalse(testCharacter.isVictim());
    }

    @Test
    public void setVictim() throws Exception {
        assertFalse(testCharacter.isVictim());

        testCharacter.setVictim(true);

        assertTrue(testCharacter.isVictim());
    }

    @Test
    public void isAccused() throws Exception {
        assertFalse(testCharacter.isAccused());
    }

    @Test
    public void setAccused() throws Exception {
        assertFalse(testCharacter.isAccused());

        testCharacter.setAccused(true);

        assertTrue(testCharacter.isAccused());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("failure - returned name not correct", "test name", testCharacter.getName());
    }

    @Test
    public void addItem() throws Exception {
        assertFalse(testCharacter.getItemList().contains(testItem, true));

        testCharacter.addItem(testItem);

        assertTrue(testCharacter.getItemList().contains(testItem, true));
    }

    @Test
    public void addClue() throws Exception {
        assertFalse(testCharacter.getClueList().contains(testClue, true));

        testCharacter.addClue(testClue);

        assertTrue(testCharacter.getClueList().contains(testClue, true));
    }

    @Test
    public void getItemList() throws Exception {
        assertNotNull(testCharacter.getItemList());
    }

    @Test
    public void getClueList() throws Exception {
        assertNotNull(testCharacter.getItemList());
    }
}