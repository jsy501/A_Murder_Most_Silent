package com.noname.mrch;

import com.noname.mrch.gameobject.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 29/11/2016.
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
    public void getItemArray() throws Exception {
        assertNotNull(itemManager.getItemArray());
    }

    @Test
    public void getKey() throws Exception {
        assertNotNull(itemManager.getKey());

        assertEquals("failure - item is not a key", testKey, itemManager.getKey());
    }

}