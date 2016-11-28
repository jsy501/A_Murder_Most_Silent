package com.noname.mrch;

import com.noname.mrch.gameobject.Player;
import com.noname.mrch.gameobject.Room;

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
        player = new Player("test name", 0);
    }

    @After
    public void tearDown() throws Exception {
        player = null;
    }

    @Test
    public void setLocationTest() throws Exception {
        assertEquals("failure - the players room does not initialise to null", null, player.getLocation());

        Room testroom = new Room(0, "test room", false);
        player.setLocation(testroom);

        assertEquals("failure - room is not the room the player is in", testroom, player.getLocation());




    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("failure - player name not correct", "test name", player.getName());
    }

    @Test
    public void getPersonalityTest() throws Exception {
        assertEquals("failure - players personality is not correct", 0, player.getPersonality());

    }

    @Test
    public void getLocationTest() throws Exception {
        assertEquals("failure - player does not initialise location to null", null, player.getLocation());


        Room testroom = new Room(0,"Test Room",false);
        player.setLocation(testroom);

        assertEquals("failure - player location not correct", testroom, player.getLocation());
    }
}