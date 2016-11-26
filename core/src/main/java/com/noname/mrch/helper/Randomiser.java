package com.noname.mrch.helper;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameObject.Item;

public class Randomiser {
    public Array generateRandomArray(Array inputArray, int outputSize){
        if (outputSize > inputArray.size){
            throw new IllegalArgumentException("Output size cannot be greater than input array size");
        }

        Array outputArray = new Array();

        inputArray.shuffle();
        for (int i=0; i<outputSize; i++){
            outputArray.add(inputArray.pop());
        }
        return outputArray;
    }

    public Array<Item> randomiseItems(Array<Item> inputArray){
        for (int i = 0; i < inputArray.size-1; i++){
            inputArray.get(i).setReturnItem(inputArray.get(i+1));
        }
        return inputArray;
    }
}
