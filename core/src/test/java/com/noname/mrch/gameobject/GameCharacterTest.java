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
        testCharacter = new GameCharacter(0 , "test name", Personality.AGGRESSIVE, false, false, false, "test greeting", "test negative response");

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
    public void getIdTest() throws Exception {
        assertEquals("failure - testCharacter did not initialise with correct id", 0, testCharacter.getId());
    }

    @Test
    public void getGreetingTest() throws Exception {
        assertEquals("failure - returned greeting was not correct", "test greeting", testCharacter.getGreeting());
    }

    @Test
    public void getResponseTest() throws Exception {
        assertEquals("failure - returned response was not correct", "test negative response", testCharacter.getResponse());
    }

    @Test
    public void getPersonalityTest() throws Exception {
        assertEquals("failure - returned personality was not correct", Personality.AGGRESSIVE, testCharacter.getPersonality());
    }

    @Test
    public void isMurdererTest() throws Exception {
        assertFalse(testCharacter.isMurderer());
    }

    @Test
    public void setMurdererTest() throws Exception {
        assertFalse(testCharacter.isMurderer());

        testCharacter.setMurderer(true);

        assertTrue(testCharacter.isMurderer());
    }

    @Test
    public void isVictimTest() throws Exception {
        assertFalse(testCharacter.isVictim());
    }

    @Test
    public void setVictimTest() throws Exception {
        assertFalse(testCharacter.isVictim());

        testCharacter.setVictim(true);

        assertTrue(testCharacter.isVictim());
    }

    @Test
    public void isAccusedTest() throws Exception {
        assertFalse(testCharacter.isAccused());
    }

    @Test
    public void setAccusedTest() throws Exception {
        assertFalse(testCharacter.isAccused());

        testCharacter.setAccused(true);

        assertTrue(testCharacter.isAccused());
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("failure - returned name not correct", "test name", testCharacter.getName());
    }

    @Test
    public void addItemTest() throws Exception {
        assertFalse(testCharacter.getItemList().contains(testItem, true));

        testCharacter.addItem(testItem);

        assertTrue(testCharacter.getItemList().contains(testItem, true));
    }

    @Test
    public void addClueTest() throws Exception {
        assertFalse(testCharacter.getClueList().contains(testClue, true));

        testCharacter.addClue(testClue);

        assertTrue(testCharacter.getClueList().contains(testClue, true));
    }

    @Test
    public void getItemListTest() throws Exception {
        assertNotNull(testCharacter.getItemList());
    }

    @Test
    public void getClueListTest() throws Exception {
        assertNotNull(testCharacter.getItemList());
    }
}