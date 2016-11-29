package com.noname.mrch.gameobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by nxn1 on 29/11/2016.
 */
public class RoomTest {
    Room room;
    Room lockedRoom;

    @Before
    public void setUp() throws Exception {
        room = new Room(0,"test room",false);
        lockedRoom = new Room(0,"test locked room", true);
    }

    @After
    public void tearDown() throws Exception {
        room = null;
        lockedRoom = null;
    }

    @Test
    public void addActorTest() throws Exception {

    }

    @Test
    public void removeActorTest() throws Exception {

    }

    @Test
    public void getStageTest() throws Exception {

    }

}