package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxBuild;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.GameCharacter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

@RunWith(GdxTest.class)
public class CharacterManagerTest {
    private CharacterManager characterManager;

    @Before
    public void setUp() throws Exception {
        FileHandle file = new FileHandle("core/assets.properties");
        Properties properties = new Properties();
        try {
            properties.load(file.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath = properties.getProperty("characterJsonPath");

        characterManager = new CharacterManager(new FileHandle(filePath));
    }

    @Test
    public void getMurderer() throws Exception {
        GameCharacter testCharacter = characterManager.getMurderer();
        assertTrue("Returned character not murderer", testCharacter.isMurderer());
    }

    @Test
    public void getVictim() throws Exception {
        GameCharacter testCharacter = characterManager.getVictim();
        assertTrue("Returned character not victim", testCharacter.isVictim());
    }

    @Test
    public void getCharacterArray() throws Exception {
        GameCharacter murderer = characterManager.getMurderer();
        GameCharacter victim = characterManager.getVictim();

        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        assertTrue("Character array doesn't contain murderer", characterArray.contains(murderer, false));
        assertFalse("Character array contains victim", characterArray.contains(victim, false));
        assertTrue("Incorrect character count", characterArray.size == CharacterManager.CHARACTER_COUNT - 1);
    }

}