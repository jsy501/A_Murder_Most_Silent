package com.noname.mrch.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.noname.mrch.GdxTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Room Unit tests
 */

@RunWith(GdxTest.class)
public class RoomTest{
    Room testRoom;
    GameCharacter testCharacter;
    Clue testClue;
    Item testItem;


    @Before
    public void setUp() throws Exception {
        ShaderProgram program = Mockito.mock(ShaderProgram.class);
        SpriteBatch batch = new SpriteBatch(100, program);

        testRoom = new Room(0, "test room", false, batch);

        testCharacter = new GameCharacter();
        testClue = new Clue();
        testItem = new Item();

    }

    @After
    public void tearDown() throws Exception {
        testRoom = null;
        testCharacter = null;
        testClue = null;
        testItem = null;
    }
    @Test
    public void addCharacterTest() throws Exception {
        assertFalse(testRoom.getCharacterList().contains(testCharacter, true));

        testRoom.addCharacter(testCharacter);

        assertTrue(testRoom.getCharacterList().contains(testCharacter, true));
    }

    @Test
    public void getCharacterListTest() throws Exception {
        assertNotNull(testRoom.getCharacterList());

    }

    @Test
    public void addItemTest() throws Exception {
        assertFalse(testRoom.getItemList().contains(testItem, true));

        testRoom.addItem(testItem);

        assertTrue(testRoom.getItemList().contains(testItem, true));

    }

    @Test
    public void addClueTest() throws Exception {
        assertFalse(testRoom.getClueList().contains(testClue, true));

        testRoom.addClue(testClue);

        assertTrue(testRoom.getClueList().contains(testClue, true));

    }

    @Test
    public void getItemListTest() throws Exception {
        assertNotNull(testRoom.getItemList());
    }

    @Test
    public void getClueListTest() throws Exception {
        assertNotNull(testRoom.getClueList());
    }

    @Test
    public void removeClueTest() throws Exception {
        assertFalse(testRoom.getClueList().contains(testClue, true));

        testRoom.addClue(testClue);

        assertTrue(testRoom.getClueList().contains(testClue, true));

        testRoom.removeClue(testClue);

        assertFalse(testRoom.getClueList().contains(testClue, true));
    }

    @Test
    public void removeItemTest() throws Exception {
        assertFalse(testRoom.getItemList().contains(testItem, true));

        testRoom.addItem(testItem);

        assertTrue(testRoom.getItemList().contains(testItem, true));

        testRoom.removeItem(testItem);

        assertFalse(testRoom.getItemList().contains(testItem, true));
    }

    @Test
    public void getIdTest() throws Exception {
        assertEquals("failure - id is not 0", 0, testRoom.getId());
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("failure - name is not 'test name'", "test room", testRoom.getName());
    }

}