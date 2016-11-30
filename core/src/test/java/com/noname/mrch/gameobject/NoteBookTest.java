package com.noname.mrch.gameobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * NoteBook unit Test.
 */
public class NoteBookTest {
    Clue testClue;
    Clue containsTestClue;
    Item testItem;
    Item containsTestItem;

    NoteBook noteBook;

    @Before
    public void setUp() throws Exception {
        noteBook = NoteBook.getInstance();

        testClue = new Clue(0);
        containsTestClue = new Clue(1);

        testItem = new Item(2);
        containsTestItem = new Item(3);

    }

    @After
    public void tearDown() throws Exception {
        noteBook = null;
        testClue = null;
        containsTestClue = null;
        testItem = null;
        containsTestItem = null;

    }

    @Test
    public void addClueTest() throws Exception {
        assertFalse("Failure - noteBook already contains element at initialisation", noteBook.contains(testClue));

        noteBook.addClue(testClue);

        assertTrue("failure - noteBook does not contain testClue", noteBook.contains(testClue));
    }

    @Test
    public void addItemTest() throws Exception {
        assertFalse("Failure - noteBook already contains element at initialisation", noteBook.contains(testItem));

        noteBook.addItem(testItem);

        assertTrue("failure - noteBook does not contain testClue", noteBook.contains(testItem));

    }

    @Test
    public void getClueTest() throws Exception {
        noteBook.addClue(testClue);
        assertTrue("failure - test clue not added to noteBook", noteBook.contains(testClue));

        assertEquals("failure - test clue was not returned", testClue, noteBook.getClue(0));
    }

    @Test
    public void getItemTest() throws Exception {
        noteBook.addItem(testItem);
        assertTrue("failure - test item was not added to noteBook", noteBook.contains(testClue));

        assertEquals("failure - test clue was not returned", testItem , noteBook.getItem(2));
    }

    @Test
    public void containsClueTest() throws Exception {
        assertFalse("failure - array does not contain any elements", noteBook.contains(containsTestClue));

        noteBook.addClue(containsTestClue);

        assertTrue("failure - array does contain elements", noteBook.contains(containsTestClue));
    }

    @Test
    public void containsItemTest() throws Exception {
        assertFalse("failure - array does not contain any elements", noteBook.contains(containsTestItem));

        noteBook.addItem(containsTestItem);

        assertTrue("failure - array does contain elements", noteBook.contains(containsTestItem));

    }

    @Test
    public void getClueListTest() throws Exception {
        assertNotNull(noteBook.getClueList());
    }

    @Test
    public void getItemListTest() throws Exception {
        assertNotNull(noteBook.getClueList());
    }

}