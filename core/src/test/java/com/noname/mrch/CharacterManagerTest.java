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
        characterManager = CharacterManager.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        characterManager = null;
    }

    @Test
    public void getMurderer() throws Exception {
        assertNotNull(characterManager.getMurderer());

        assertTrue(characterManager.getMurderer().isMurderer());
    }

    @Test
    public void getVictim() throws Exception {
        assertNotNull(characterManager.getVictim());

        assertTrue(characterManager.getVictim().isVictim());

    }

    @Test
    public void getCharacterArray() throws Exception {
        assertNotNull(characterManager.getCharacterArray());
    }

    @Test
    public void characterArrayNotContainMurderer() throws Exception {
        assertFalse(characterManager.getCharacterArray().contains(characterManager.getMurderer(),true));
    }

    @Test
    public void characterArrayNotContainVictim() throws Exception {
        assertFalse(characterManager.getCharacterArray().contains(characterManager.getVictim(), true));

    }

}