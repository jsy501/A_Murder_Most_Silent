package com.noname.mrch;

import com.noname.mrch.gameobject.ClueType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Clue Manager Unit Test
 */
public class ClueManagerTest {
    ClueManager clueManager;

    @Before
    public void setUp() throws Exception {
        clueManager = ClueManager.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        clueManager = null;
    }

    @Test
    public void getMotiveClue() throws Exception {
        assertNotNull(clueManager.getMotiveClue());

        assertTrue(clueManager.getMotiveClue().getClueType() == ClueType.Motive);
    }

    @Test
    public void getWeaponClue() throws Exception {
        assertNotNull(clueManager.getWeaponClue());

        assertTrue(clueManager.getWeaponClue().getClueType() == ClueType.Weapon);
    }

    @Test
    public void getAppearanceClue() throws Exception {
        assertNotNull(clueManager.getAppearanceClue());
    }

}