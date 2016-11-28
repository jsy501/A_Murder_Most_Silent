package com.noname.mrch.helper;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.GameCharacter;

public final class InitUtil {
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

    /**
     * Filter out clues that has no related character in the given array of characters
     * and include only one motive clue
     *
     * @param inputClueArray Array of clues to be filtered
     * @param inputPersonArray Array of characters used to filter
     * @return Array of clues with at least one related character from the personArray
     */

    public static Array<Clue> filterClues(Array<Clue> inputClueArray, Array<GameCharacter> inputPersonArray){
        /*
         the first n clues loaded from json are character unique motive clues
         where n is the total number of character roster
          */

        Array<Clue> outputClueArray = new Array<>();

        for (int i = GameCharacter.ROSTER_NUMBER-1; i < inputClueArray.size; i++){
            for (int j = 0; j < inputPersonArray.size; j++){
                if (inputClueArray.get(i).getRelatedCharId().contains(inputPersonArray.get(j).getId(), false)){
                    outputClueArray.add(inputClueArray.get(i));
                    break;
                }
            }
        }

        return outputClueArray;
    }
}
