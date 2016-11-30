package com.noname.mrch.gameobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Room unit tests
 */
public class RoomTest {
    Room room;
    Room lockedRoom;
    Room stageTest;
    Stage testStage;

    @Before
    public void setUp() throws Exception {
        room = new Room(0,"test room",false);
        lockedRoom = new Room(0,"test locked room", true);

        testStage = new Stage();
    }

    @After
    public void tearDown() throws Exception {
        room = null;
        lockedRoom = null;
        stageTest = null;
        testStage = null;
    }
}