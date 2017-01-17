package com.noname.mrch;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.noname.mrch.helper.AssetLoader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


@RunWith(GdxTest.class)
public class ClueManagerTest {
    private AssetLoader assetLoader;

    private CharacterManager characterManager;
    private ClueManager clueManager;

    @Before
    public void setUp() throws Exception {
        assetLoader = new AssetLoader();
        characterManager = new CharacterManager(assetLoader);
        clueManager = new ClueManager(assetLoader, characterManager);
    }

    @After
    public void tearDown() throws Exception {
        assetLoader.dispose();
        assetLoader = null;
        characterManager = null;
        clueManager = null;
    }


    @Test
    public void getMotiveClue() throws Exception {
        assertNotNull("Motive clue not initialised", clueManager.getMotiveClue());
    }

    @Test
    public void getWeaponClue() throws Exception {
        assertNotNull("Weapon clue not initialised", clueManager.getWeaponClue());
    }

    @Test
    public void getAppearanceClueArray() throws Exception {
        assertNotNull("Appearance clues not initialised", clueManager.getAppearanceClueArray());
    }

    @Test
    public void getIrrelevantClueArray() throws Exception {
        assertNotNull("Irrelevant clues not initialised", clueManager.getIrrelevantClueArray());
    }

}