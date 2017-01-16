package com.noname.mrch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
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
    private AssetLoader assetLoader;

    // clues linked to the murderer
    private Clue motiveClue;
    private Clue weaponClue;
    private Array<Clue> appearanceClueArray;

    // appearance clues not linked to the murderer
    private Array<Clue> irrelevantClueArray;

    private TextureAtlas textureAtlas;

    public ClueManager(AssetLoader assetLoader, CharacterManager characterManager){
        this.characterManager = characterManager;
        this.assetLoader = assetLoader;

        textureAtlas = assetLoader.manager.get(assetLoader.clueTexturePath);

        initMotiveClue();
        initWeaponClue();
        initAppearanceClue();
    }

    /**
     * Assign motive clue and check error
     */
    private void initMotiveClue(){

        Array<Clue> totalClueArray = assetLoader.totalMotiveClueArray;

        //motive clues are unique for every character
        motiveClue = totalClueArray.get(characterManager.getMurderer().getId() - GameCharacter.ID_OFFSET);
        motiveClue.setImage(textureAtlas.findRegion(String.valueOf(motiveClue.getId())));

        //json import check
        if (motiveClue.getClueType() != ClueType.MOTIVE ||
                !motiveClue.getRelatedCharId().contains(characterManager.getMurderer().getId(), false)){
            throw new RuntimeException("Invalid json format");
        }
    }

    /**
     * Choose one weapon clue randomly from a total weapon clue array and point to the murderer.
     * Check json import error
     */
    private void initWeaponClue(){
        Array<Clue> totalClueArray = assetLoader.totalWeaponClueArray;

        weaponClue = totalClueArray.random();

        //json import check
        if (weaponClue.getClueType() != ClueType.WEAPON){
            throw new RuntimeException("Invalid json format");
        }

        weaponClue.getRelatedCharId().add(characterManager.getMurderer().getId());
        weaponClue.setImage(textureAtlas.findRegion(String.valueOf(weaponClue.getId())));
    }

    /**
     * Choose appearance clues that point to the murderer from a total appearance clue array.
     * Check json import error
     */
    private void initAppearanceClue(){
        Array<Clue> totalAppearanceClueArray = new Array<>(assetLoader.totalAppearanceClueArray);

        irrelevantClueArray = new Array<>();
        appearanceClueArray = new Array<>();

        GameCharacter murderer = characterManager.getMurderer();
        for (int i = 0; i <totalAppearanceClueArray.size ; i++){
            Clue clue = totalAppearanceClueArray.get(i);
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
