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
    public void setLocation() throws Exception {
        // create player
        // check location
        // player.setLocation()
        // check new location
        // asserts
        Room testroom = new Room(0,"Test Room",false);

        Room expected = testroom;
        Room actual = player.getLocation();
        assertEquals("failure - player location not correct", expected, actual);



    }

    @Test
    public void getName() throws Exception {
        String expected = "test";
        String actual = player.getName();
        assertEquals("failure - player name not correct",expected,actual);
    }

    @Test
    public void getPersonality() throws Exception {
        int expected = 0;
        int actual = player.getPersonality();

        assertEquals("failure - players personality is not correct", expected, actual);

    }

    @Test
    public void getLocation() throws Exception {
        Room testroom = new Room(0, "test room", false);
        player.setLocation(testroom);

        Room expected = testroom;
        Room actual = player.getLocation();
        assertEquals("failure - room is not the room the player is in", expected, actual);
    }
}