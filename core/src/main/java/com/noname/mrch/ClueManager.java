package com.noname.mrch;

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

    private Clue motiveClue;
    private Clue weaponClue;
    private Array<Clue> appearanceClue;

    public ClueManager(AssetLoader assetLoader, CharacterManager characterManager){
        this.characterManager = characterManager;
        this.assetLoader = assetLoader;

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
    }

    /**
     * Choose 3 appearance clues that point to the murderer from a total appearance clue array
     */

    private void initAppearanceClue(){
        Array<Clue> totalClueArray = assetLoader.totalAppearanceClueArray;

        appearanceClue = new Array<>();

        GameCharacter murderer = characterManager.getMurderer();
        for (int i = 0; i < totalClueArray.size && appearanceClue.size <= 3; i++){
            if (totalClueArray.get(i).getRelatedCharId().contains(murderer.getId(), false)){
                appearanceClue.add(totalClueArray.get(i));
            }

            //json import check
            if (totalClueArray.get(i).getClueType() != ClueType.APPEARANCE){
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

    public Array<Clue> getAppearanceClue() {
        return appearanceClue;
    }
}
