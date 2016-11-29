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
 */

public class ClueManager {
    private static ClueManager Instance = new ClueManager();

    private Clue motiveClue;
    private Clue weaponClue;
    private Array<Clue> appearanceClue;

    ClueManager(){
        AssetLoader assetLoader = AssetLoader.getInstance();

        initMotiveClue(assetLoader);
        initWeaponClue(assetLoader);
        initAppearanceClue(assetLoader);
    }

    public static ClueManager getInstance(){
        return Instance;
    }

    private void initMotiveClue(AssetLoader assetLoader){

        Array<Clue> totalClueArray = assetLoader.totalMotiveClue;

        CharacterManager characterManager = CharacterManager.getInstance();

        motiveClue = totalClueArray.get(characterManager.getMurderer().getId() - GameCharacter.ID_OFFSET);

        //json import check
        if (motiveClue.getClueType() != ClueType.Motive ||
                !motiveClue.getRelatedCharId().contains(characterManager.getMurderer().getId(), false)){
            throw new RuntimeException("Invalid json format");
        }
    }

    private void initWeaponClue(AssetLoader assetLoader){
        Array<Clue> totalClueArray = assetLoader.totalWeaponClue;

        weaponClue = totalClueArray.random();

        //json import check
        if (weaponClue.getClueType() != ClueType.Weapon){
            throw new RuntimeException("Invalid json format");
        }

        weaponClue.getRelatedCharId().add(CharacterManager.getInstance().getMurderer().getId());
    }

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
