package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.ClueType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

@RunWith(GdxTest.class)
public class ClueManagerTest {
    private CharacterManager characterManager;
    private ClueManager clueManager;

    @Before
    public void setUp() throws Exception {

        FileHandle file = new FileHandle("core/assets.properties");
        Properties properties = new Properties();
        try {
            properties.load(file.read());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String characterJsonPath = properties.getProperty("characterJsonPath");
        String motiveClueJsonPath = properties.getProperty("motiveClueJsonPath");
        String weaponClueJsonPath = properties.getProperty("weaponClueJsonPath");
        String appearanceClueJsonPath = properties.getProperty("appearanceClueJsonPath");

        characterManager = new CharacterManager(new FileHandle(characterJsonPath));
        clueManager = new ClueManager(new FileHandle(motiveClueJsonPath),
                new FileHandle(weaponClueJsonPath),
                new FileHandle(appearanceClueJsonPath),
                Mockito.mock(TextureAtlas.class),
                characterManager);
    }

    @Test
    public void getMotiveClue() throws Exception {
        Clue test = clueManager.getMotiveClue();
        assertTrue("Wrong clue type returned", test.getClueType() == ClueType.MOTIVE);
        assertTrue("Clue not linked to murderer", test.getRelatedCharId().contains(characterManager.getMurderer().getId(), false));
    }

    @Test
    public void getWeaponClue() throws Exception {
        Clue test = clueManager.getWeaponClue();
        assertTrue("Wrong clue type returned", test.getClueType() == ClueType.WEAPON);
    }

    @Test
    public void getAppearanceClueArray() throws Exception {
        Array<Clue> clueArray = clueManager.getAppearanceClueArray();
        for (Clue clue : clueArray){
            assertTrue("Wrong clue type return", clue.getClueType() == ClueType.APPEARANCE);
            assertTrue("Clue not linked to murderer", clue.getRelatedCharId().contains(characterManager.getMurderer().getId(), false));
        }
    }

    @Test
    public void getIrrelevantClueArray() throws Exception {
        Array<Clue> clueArray = clueManager.getIrrelevantClueArray();
        for (Clue clue : clueArray){
            assertTrue("Wrong clue type return", clue.getClueType() == ClueType.APPEARANCE);
            assertFalse("Clue linked to murderer", clue.getRelatedCharId().contains(characterManager.getMurderer().getId(), false));
        }
    }

}