package com.noname.mrch.helper;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.Item;
import com.noname.mrch.gameObject.Person;

public class Randomiser {
    public Array generateRandomArray(Array inputArray, int outputSize){
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

    public Array<Item> generateItemLinks (Array<Item> inputArray){
        for (int i = 0; i < inputArray.size-1; i++){
            inputArray.get(i).setReturnItem(inputArray.get(i+1));
        }
        return inputArray;
    }

    /**
     * Filter out clues that has no related character in the given array of characters
     *
     * @param clueArray Array of clues to be filtered
     * @param personArray Array of characters used to filter
     * @return Array of clues with at least one related character from the personArray
     */

    public Array<Clue> filterClues(Array<Clue> clueArray, Array<Person> personArray){
        for (int i = 0; i < clueArray.size; i++){
            boolean idMatch = false;

            for (int j = 0; j < personArray.size; j++){
                if (clueArray.get(i).getRelatedCharId().contains(personArray.get(j).getId(), false)){
                    idMatch = true;
                    break;
                }
            }

            if(!idMatch){
                clueArray.removeIndex(i);
            }
        }

        return clueArray;
    }
}
