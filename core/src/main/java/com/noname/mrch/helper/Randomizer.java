package com.noname.mrch.helper;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.CharacterManager;
import com.noname.mrch.ClueManager;
import com.noname.mrch.ItemManager;
import com.noname.mrch.RoomManager;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.ClueTag;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.Room;

public final class Randomizer {
    /**
     * Generate randomly ordered array of desired size from the given array
     *
     * @param inputArray Original array to be used
     * @param outputSize Desired size of output array
     */
    public static Array generateRandomArray(Array inputArray, int outputSize){
        if (outputSize > inputArray.size){
            throw new IllegalArgumentException("Output size cannot be greater than input array size");
        }

        Array outputArray = new Array<>();

        inputArray.shuffle();
        for (int i=0; i<outputSize; i++){
            outputArray.add(inputArray.pop());
        }
        return outputArray;
    }

    public static void randomDistributeGameObject(ClueManager clueManager, ItemManager itemManager,
                                                  CharacterManager characterManager, RoomManager roomManager){

        distributeClue(clueManager, characterManager, roomManager);
        distributeItem(itemManager, characterManager, roomManager);
        distributeCharacter(characterManager, roomManager);
    }

    private static void distributeClue(ClueManager clueManager, CharacterManager characterManager, RoomManager roomManager){
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        Array<Room> roomArray = roomManager.getRoomArray();

        roomManager.getLockedRoom().addClue(clueManager.getWeaponClue());

        //remove murderer from character array first so the murderer is not given motive clues
        characterArray.removeValue(characterManager.getMurderer(),false);

        characterArray.random().addClue(clueManager.getMotiveClue());

        //murderer added back again to irrelevant clues can be given
        characterArray.add(characterManager.getMurderer());

        Array<Room> tempRoomArray = new Array<>();
        tempRoomArray.addAll(roomArray);
        tempRoomArray.shuffle();

        Array<GameCharacter> tempCharArray = new Array<>();
        tempCharArray.addAll(characterArray);
        tempCharArray.shuffle();

        Array<Clue> tempRelevantClueArray = new Array<>();
        tempRelevantClueArray.addAll(clueManager.getAppearanceClueArray());
        tempRelevantClueArray.shuffle();

        // add relevant appearance clues to either a room or a character depending on the clue tag
        // the max number of relevant clue is limited to 3/4 of number of clue holders so the other 1/4
        // get irrelevant clues

        int maxRelevantClueNum = (int) ((roomArray.size + characterArray.size) * 0.75);

        int relevantClueCount = 0;
        while (relevantClueCount < maxRelevantClueNum && tempRelevantClueArray.size > 0){
            Clue clueToAssign = tempRelevantClueArray.pop();
            if (clueToAssign.getClueTag() == ClueTag.PHYSICAL && tempRoomArray.size > 0){
                tempRoomArray.pop().addClue(clueToAssign);
                relevantClueCount++;
            }
            else if (clueToAssign.getClueTag() == ClueTag.VERBAL&& tempCharArray.size > 0){
                tempCharArray.pop().addClue(clueToAssign);
                relevantClueCount++;
            }
        }


        Array<Clue> tempIrrelevantClueArray = new Array<>();
        tempIrrelevantClueArray.addAll(clueManager.getIrrelevantClueArray());
        tempIrrelevantClueArray.shuffle();


        /*
        add irrelevant clues to every clue holder that doesn't have a clue
        the max number of irrelevant clues should be less than or equal to half of relevant appearance
        clues + motive clue + weapon clue
        */
        int maxIrrelevantClueNum = (relevantClueCount + 2) / 2;
        int irrelevantClueCount = 0;
        while(irrelevantClueCount < maxIrrelevantClueNum && tempIrrelevantClueArray.size > 0){
            Clue clueToAssign = tempIrrelevantClueArray.pop();
            if (clueToAssign.getClueTag() == ClueTag.PHYSICAL && tempRoomArray.size > 0){
                tempRoomArray.pop().addClue(clueToAssign);
                irrelevantClueCount++;
            }
            else if (clueToAssign.getClueTag() == ClueTag.VERBAL && tempCharArray.size > 0){
                tempCharArray.pop().addClue(clueToAssign);
                maxRelevantClueNum++;
            }
        }
    }

    private static void distributeItem(ItemManager itemManager, CharacterManager characterManager, RoomManager roomManager){
        Array<Item> itemArray = itemManager.getItemArray();
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        Array<Room> roomArray = roomManager.getRoomArray();

        //if the last character in the array is the murderer swap its place with the second last,
        //so the key is not given to the murderer
        if (characterArray.peek().isMurderer()){
            characterArray.swap(characterArray.size-1, characterArray.size-2);
        }

        itemArray.add(itemManager.getKey());
        for (int i = 0; i < characterArray.size; i++) {
            //assign every item but the first one to every character
            //the first item is to be found in a room
            characterManager.getCharacterArray().get(i).addItem(itemManager.getItemArray().get(i+1));

            //create links between items
            itemManager.getItemArray().get(i).setReturnItem(itemManager.getItemArray().get(i+1));
        }
        itemArray.removeValue(itemManager.getKey(), false);

        //the first item is assigned to a random room
        roomArray.random().addItem(itemArray.get(0));
    }

    private static void distributeCharacter(CharacterManager characterManager, RoomManager roomManager){
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        Array<Room> roomArray = roomManager.getRoomArray();

        characterArray.shuffle();
        int cnt = 0;
        Array.ArrayIterator<GameCharacter> iterator = new Array.ArrayIterator<>(characterArray);
        while(iterator.hasNext()){
            int roomIndex = cnt % (roomArray.size+1); //+1 for the locked room
            if (!roomArray.get(roomIndex).isLocked()){
                roomArray.get(roomIndex).addCharacter(iterator.next());
            }
            cnt++;
        }
    }
}




















