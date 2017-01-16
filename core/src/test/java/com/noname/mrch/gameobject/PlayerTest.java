package com.noname.mrch.gameobject;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Player Tests
 */
public class PlayerTest {
    Player player;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        player = null;
    }


    @Test
    public void getNameTest() throws Exception {
        assertEquals("failure - player name not correct", "test name", player.getName());
    }

}