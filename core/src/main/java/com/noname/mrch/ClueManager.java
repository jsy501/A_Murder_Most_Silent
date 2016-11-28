package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.ClueType;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.helper.InitUtil;

public class ClueManager {
    private Array<Clue> clueArray;
    private Clue motiveClue;

    ClueManager(CharacterManager characterManager){
        clueArray = new Array<>();

        Json json = new Json();
        Array<Clue> totalClueList = json.fromJson(Array.class, Clue.class, Gdx.files.local("clues.json"));

        motiveClue = totalClueList.get(characterManager.getMurderer.getId() - GameCharacter.ID_OFFSET);

        //json import check
        if (motiveClue.getClueType() != ClueType.Motive ||
                !motiveClue.getRelatedCharId().contains(characterManager.getMurderer.getId(), false)){
            throw new RuntimeException("Invalid json format");
        }

        clueArray.add(motiveClue);

        clueArray = InitUtil.filterClues(totalClueList, characterManager.getCharacterArray());
    }

    public Clue getMotiveClue(){
        return motiveClue;
    }

    public Array<Clue> getClueArray(){
        return clueArray;
    }
}
