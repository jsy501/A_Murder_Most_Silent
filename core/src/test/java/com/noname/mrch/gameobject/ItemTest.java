package com.noname.mrch.gameobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Item unit tests.
 */
public class ItemTest {
    Item item;
    Item key;

    @Before
    public void setUp() throws Exception {
        key = new Item(1, "test return item", "This is the return item test", 999, null, true);
        item = new Item(0, "Test Item", "This is a test item", 100,key,  false);
    }

    @After
    public void tearDown() throws Exception {
        item = null;
    }

    @Test
    public void getIdTest() throws Exception {
        assertEquals("failure - item not initialised with id 0", 0, item.getId());
    }

    @Test
    public void getDescriptionTest() throws Exception {
        assertEquals("failure - item not initialised with test description", "This is a test item", item.getDescription());

    }

    @Test
    public void getLinkedPersonTest() throws Exception {
        assertEquals("failure - item not initialised with test linked person", 100, item.getLinkedPersonId());

    }

    @Test
    public void getReturnItemTest() throws Exception {
        assertEquals("failure - returned item was not the test return item", key, item.getReturnItem());
        assertEquals("failure - returned item was not null", null, key.getReturnItem());
    }

    @Test
    public void setReturnItemTest() throws Exception {
        assertEquals("failure - returned item was not initialised correctly", key, item.getReturnItem());

        Item testItem = new Item(2,"test","this is an item",999,null,false);

        item.setReturnItem(testItem);
        assertEquals("failure - return item was not set", testItem, item.getReturnItem());

    }

    @Test
    public void isKeyTest() throws Exception {
        assertEquals("failure - item is a key", false, item.isKey());

        assertEquals("failure - item is not a key", true, key.isKey());
    }

}