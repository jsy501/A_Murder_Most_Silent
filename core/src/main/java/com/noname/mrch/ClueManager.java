package com.noname.mrch;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.ClueType;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.AssetLoader;

/**
 *  Initialises and manages clues that point to the murderer
 *  Dependent on CharacterManager
 */

public class ClueManager {
    private CharacterManager characterManager;

    // clues linked to the murderer
    private Clue motiveClue;
    private Clue weaponClue;
    private Array<Clue> appearanceClueArray;

    // appearance clues not linked to the murderer
    private Array<Clue> irrelevantClueArray;

    public ClueManager(AssetLoader assetLoader, CharacterManager characterManager){
        this.characterManager = characterManager;

        TextureAtlas textureAtlas = assetLoader.manager.get(assetLoader.clueTexturePath);

        Array<Clue> totalMotiveArray = assetLoader.totalMotiveClueArray;
        Array<Clue> totalWeaponArray = assetLoader.totalWeaponClueArray;
        Array<Clue> totalAppearanceArray = new Array<>(assetLoader.totalAppearanceClueArray);

        initMotiveClue(totalMotiveArray, textureAtlas);
        initWeaponClue(totalWeaponArray, textureAtlas);
        initAppearanceClue(totalAppearanceArray, textureAtlas);
    }

    /**
     * Constructor used only for testing
     * @param characterManager character manager used to initialise
     */

    public ClueManager(FileHandle motiveJsonFile, FileHandle weaponJsonFile, FileHandle appearanceJsonFile,
                       TextureAtlas textureAtlas, CharacterManager characterManager){
        this.characterManager = characterManager;
        Json json = new Json();

        Array<Clue> totalMotiveArray = json.fromJson(Array.class, Clue.class, motiveJsonFile);
        Array<Clue> totalWeaponArray = json.fromJson(Array.class, Clue.class, weaponJsonFile);
        Array<Clue> totalAppearanceArray = json.fromJson(Array.class, Clue.class, appearanceJsonFile);

        initMotiveClue(totalMotiveArray, textureAtlas);
        initWeaponClue(totalWeaponArray, textureAtlas);
        initAppearanceClue(totalAppearanceArray, textureAtlas);
    }

    /**
     * Assign motive clue and check error
     * @param totalMotiveArray Total roster of motive clue array
     * @param textureAtlas texture atlas for image assignment
     */
    private void initMotiveClue(Array<Clue> totalMotiveArray, TextureAtlas textureAtlas){
        //motive clues are unique for every character
        motiveClue = totalMotiveArray.get(characterManager.getMurderer().getId() - GameCharacter.ID_OFFSET);
        motiveClue.setImage(textureAtlas.findRegion(String.valueOf(motiveClue.getId())));
    }

    /**
     * Choose one weapon clue randomly from a total weapon clue array and point to the murderer.
     * @param totalWeaponArray Total roster of weapon clue array
     * @param textureAtlas texture atlas for image assignment
     */
    private void initWeaponClue(Array<Clue> totalWeaponArray, TextureAtlas textureAtlas){
        weaponClue = totalWeaponArray.random();

        weaponClue.getRelatedCharId().add(characterManager.getMurderer().getId());
        weaponClue.setImage(textureAtlas.findRegion(String.valueOf(weaponClue.getId())));
    }

    /**
     * Choose appearance clues that point to the murderer from a total appearance clue array.
     * Check json import error
     * @param totalAppearanceArray Total roster of appearance clue array
     * @param textureAtlas texture atlas for image assignment
     */
    private void initAppearanceClue(Array<Clue> totalAppearanceArray, TextureAtlas textureAtlas){
        irrelevantClueArray = new Array<>();
        appearanceClueArray = new Array<>();

        GameCharacter murderer = characterManager.getMurderer();
        for (int i = 0; i <totalAppearanceArray.size ; i++){
            Clue clue = totalAppearanceArray.get(i);
            clue.setImage(textureAtlas.findRegion(String.valueOf(clue.getId())));

            // if the clue is linked to the murderer move it to relevant clue array
            if (clue.getRelatedCharId().contains(murderer.getId(), false)){
                appearanceClueArray.add(clue);
            }
            // if not, move it to irrelevant clue array
            else{
                irrelevantClueArray.add(clue);
            }

            //json import check
            if (clue.getClueType() != ClueType.APPEARANCE){
                throw new RuntimeException("Invalid json format");
            }
        }
    }

    public Clue getMotiveClue(){
        return motiveClue;
    }

    public Clue getWeaponClue() {
        return weaponClue;
    }

    public Array<Clue> getAppearanceClueArray() {
        return appearanceClueArray;
    }

    public Array<Clue> getIrrelevantClueArray() {
        return irrelevantClueArray;
    }
}
