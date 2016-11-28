package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.noname.mrch.gameObject.GameCharacter;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.Item;
import com.noname.mrch.helper.ClueType;
import com.noname.mrch.helper.InitUtil;

/**
 * Initialises and holds all the game objects
 */

public class GameWorld {
    private final int PERSON_COUNT = 7;

    private Array<Item> itemList = new Array<>();
    private Array<Clue> clueList = new Array<>();
    private Array<GameCharacter> characterArray = new Array<>();

    private GameCharacter murderer;
    private GameCharacter victim;

    private Clue motiveClue;

    private Item key;

    public GameWorld(){
        initPersonList();
        initItemList();
        initClueList();

        System.out.println(characterArray);
        System.out.println("Murderer: " + murderer.toString());
        System.out.println("Victim: " + victim.toString());
        System.out.println("Motive: " + motiveClue.toString());
        System.out.println(itemList);
        System.out.println(clueList);
    }

    private void initPersonList() {
        Json json = new Json();
        Array<GameCharacter> totalCharacterList = json.fromJson(Array.class, GameCharacter.class, Gdx.files.local("persons.json"));
        characterArray = InitUtil.generateRandomArray(totalCharacterList, PERSON_COUNT);

        murderer = characterArray.pop();
        murderer.setMurderer(true);

        victim = characterArray.pop();
        victim.setVictim(true);
    }

    private void initItemList() {
        Json json = new Json();
        Array<Item> totalItemList = json.fromJson(Array.class, Item.class, Gdx.files.local("items.json"));

        //pick items that are relevant to the characters in the game
        for (int i = 0; i < characterArray.size; i++){
            int index = characterArray.get(i).getId() - GameCharacter.ID_OFFSET;
            itemList.add(totalItemList.get(index));

            if (i>0) {
                characterArray.get(i - 1).addItem(itemList.peek());
            }
        }

        key = new Item(500, "key", "It's a key", true); // the final item to be given
        characterArray.get(characterArray.size-1).addItem(key);

        itemList.add(key);

        //generate item links
        for (int i = 0; i < itemList.size-1; i++){
            itemList.get(i).setReturnItem(itemList.get(i+1));
        }
    }

    private void initClueList() {
        Json json = new Json();
        Array<Clue> totalClueList = json.fromJson(Array.class, Clue.class, Gdx.files.local("clues.json"));

        motiveClue = totalClueList.get(murderer.getId() - GameCharacter.ID_OFFSET);

        //json import check
        if (motiveClue.getClueType() != ClueType.Motive ||
                !motiveClue.getRelatedCharId().contains(murderer.getId(), false)){
            throw new RuntimeException("Invalid json format");
        }

        clueList.add(motiveClue);

        clueList = InitUtil.filterClues(totalClueList, characterArray);
    }
}
