package com.noname.mrch;

import com.noname.mrch.helper.AssetLoader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Character Manager Unit Test
 */
public class CharacterManagerTest {
    CharacterManager characterManager;

    @Before
    public void setUp() throws Exception {
//        RoomManager.createInstance();
        characterManager = CharacterManager.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        characterManager = null;
    }

    @Test
    public void getMurdererTest() throws Exception {
        assertNotNull(characterManager.getMurderer());

        assertTrue(characterManager.getMurderer().isMurderer());
    }

    @Test
    public void getVictimTest() throws Exception {
        assertNotNull(characterManager.getVictim());

        assertTrue(characterManager.getVictim().isVictim());

    }

    @Test
    public void getCharacterArrayTest() throws Exception {
        assertNotNull(characterManager.getCharacterArray());
    }

    @Test
    public void characterArrayContainMurdererTest() throws Exception {
        assertTrue(characterManager.getCharacterArray().contains(characterManager.getMurderer(),false));
    }

    @Test
    public void characterArrayNotContainVictimTest() throws Exception {
        assertFalse(characterManager.getCharacterArray().contains(characterManager.getVictim(), false));

    }

}