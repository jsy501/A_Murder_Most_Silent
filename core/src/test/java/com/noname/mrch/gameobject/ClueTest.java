package com.noname.mrch.gameobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.noname.mrch.gameobject.ClueType.Motive;
import static org.junit.Assert.*;

import com.badlogic.gdx.utils.Array;

/**
 * Created by User on 29/11/2016.
 */
public class ClueTest {
    Clue clue;
    Array<Integer> characterRelated;

    @Before
    public void setUp() throws Exception {
        clue = new Clue(0,"test clue", "test description", Motive, characterRelated, "test response");
    }

    @After
    public void tearDown() throws Exception {
        clue = null;
        characterRelated = null;
    }

    @Test
    public void getId() throws Exception {
        assertEquals("failure - test id not returned", 0, clue.getId());
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals("failure - test description not returned", "test description", clue.getDescription());
    }

    @Test
    public void getRelatedCharId() throws Exception {
        assertEquals("failure - test character id not returned", characterRelated, clue.getRelatedCharId());
    }

    @Test
    public void getClueType() throws Exception {
        assertEquals("failure - test clue type not returned", Motive, clue.getClueType());
    }

    @Test
    public void getResponse() throws Exception {
        assertEquals("failure - test response not returned", "test response", clue.getResponse());
    }

}