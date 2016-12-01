package com.noname.mrch;

import com.noname.mrch.gameobject.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Item Manager Unit Test
 */
public class ItemManagerTest {
    ItemManager itemManager;
    Item testKey;

    @Before
    public void setUp() throws Exception {
        itemManager = ItemManager.getInstance();
        testKey = new Item(500, "key", "It's a key", true);
    }

    @After
    public void tearDown() throws Exception {
        itemManager = null;
    }

    @Test
    public void getItemArrayTest() throws Exception {
        assertNotNull(itemManager.getItemArray());
    }

    @Test
    public void getKeyTest() throws Exception {
        assertNotNull(itemManager.getKey());


        //this will fail since key created in the test and the key created in item manager are two
        //different objects
        assertEquals("failure - item is not a key", testKey, itemManager.getKey());

    }

}