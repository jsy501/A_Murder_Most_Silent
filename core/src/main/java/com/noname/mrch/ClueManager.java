package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.ClueType;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.AssetLoader;

/**
 *  Initialises and manages clues
 *  Dependent on CharacterManager
 */

public class ClueManager {
    private static ClueManager Instance = null;

    private Clue motiveClue;
    private Clue weaponClue;
    private Array<Clue> appearanceClue;

    private ClueManager(){
        AssetLoader assetLoader = AssetLoader.getInstance();

        initMotiveClue(assetLoader);
        initWeaponClue(assetLoader);
        initAppearanceClue(assetLoader);
    }

    static void createInstance(){
        if (Instance == null) {
            Instance = new ClueManager();
        }
    }

    public static ClueManager getInstance(){
        if (Instance != null) {
            return Instance;
        }
        else{
            createInstance();
            return Instance;
        }
    }

    /**
     * Assign motive clue and check error
     * @param assetLoader used for loading total motive clue array
     */
    private void initMotiveClue(AssetLoader assetLoader){

        Array<Clue> totalClueArray = assetLoader.totalMotiveClue;

        CharacterManager characterManager = CharacterManager.getInstance();

        //motive clues are unique for every character
        motiveClue = totalClueArray.get(characterManager.getMurderer().getId() - GameCharacter.ID_OFFSET);

        //json import check
        if (motiveClue.getClueType() != ClueType.Motive ||
                !motiveClue.getRelatedCharId().contains(characterManager.getMurderer().getId(), false)){
            throw new RuntimeException("Invalid json format");
        }
    }

    /**
     * Choose one weapon clue randomly from a total weapon clue array and point to the murderer.
     * Check json import error
     * @param assetLoader used for loading total weapon clue array
     */

    private void initWeaponClue(AssetLoader assetLoader){
        Array<Clue> totalClueArray = assetLoader.totalWeaponClue;

        weaponClue = totalClueArray.random();

        //json import check
        if (weaponClue.getClueType() != ClueType.Weapon){
            throw new RuntimeException("Invalid json format");
        }

        weaponClue.getRelatedCharId().add(CharacterManager.getInstance().getMurderer().getId());
    }

    /**
     * Choose 3 appearance clues that point to the murderer from a total appearance clue array
     * @param assetLoader used for loading total appearance clue array
     */

    private void initAppearanceClue(AssetLoader assetLoader){
        Array<Clue> totalClueArray = assetLoader.totalAppearanceClue;

        appearanceClue = new Array<>();

        GameCharacter murderer = CharacterManager.getInstance().getMurderer();
        for (int i = 0; i < totalClueArray.size; i++){
            if (totalClueArray.get(i).getRelatedCharId().contains(murderer.getId(), false)){
                appearanceClue.add(totalClueArray.get(i));
            }

            //json import check
            if (totalClueArray.get(i).getClueType() != ClueType.Appearance){
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
