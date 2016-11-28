package com.noname.mrch.helper;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameCharacter;

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
}
