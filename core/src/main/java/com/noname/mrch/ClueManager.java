package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.ClueType;
import com.noname.mrch.gameobject.GameCharacter;

/**
 *  Initialises and manages clues
 */

public class ClueManager {
    private static ClueManager Instance = new ClueManager();

    private Clue motiveClue;
    private Clue weaponClue;
    private Array<Clue> appearanceClue;

    ClueManager(){
        initMotiveClue();
        initWeaponClue();
        initAppearanceClue();
    }

    public static ClueManager getInstance(){
        return Instance;
    }

    private void initMotiveClue(){
        Json json = new Json();
        Array<Clue> totalClueArray = json.fromJson(Array.class, Clue.class, Gdx.files.local("motive_clues.json"));

        CharacterManager characterManager = CharacterManager.getInstance();

        motiveClue = totalClueArray.get(characterManager.getMurderer().getId() - GameCharacter.ID_OFFSET);

        //json import check
        if (motiveClue.getClueType() != ClueType.Motive ||
                !motiveClue.getRelatedCharId().contains(characterManager.getMurderer().getId(), false)){
            throw new RuntimeException("Invalid json format");
        }
    }

    private void initWeaponClue(){
        Json json = new Json();
        Array<Clue> totalClueArray = json.fromJson(Array.class, Clue.class, Gdx.files.local("weapon_clues.json"));

        weaponClue = totalClueArray.random();

        //json import check
        if (weaponClue.getClueType() != ClueType.Weapon){
            throw new RuntimeException("Invalid json format");
        }

        weaponClue.getRelatedCharId().add(CharacterManager.getInstance().getMurderer().getId());
    }

    private void initAppearanceClue(){
        Json json = new Json();
        Array<Clue> totalClueArray = json.fromJson(Array.class, Clue.class, Gdx.files.local("appearance_clues.json"));

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
