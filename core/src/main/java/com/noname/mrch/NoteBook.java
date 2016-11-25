package com.noname.mrch;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameObject.Clue;

/**
 * Holds items and clues found by the player
 */

public class NoteBook {
    private static NoteBook noteBook = new NoteBook();

    private static Array<Clue> clueList = new Array<Clue>();
    private static Array<Item> itemList = new Array<Item>();

    private NoteBook(){
    }

    public static NoteBook getInstance(){
        return noteBook;
    }

    public static void addClue(Clue clue){
        clueList.add(clue);
    }

    public static void addItem(Item item){
        itemList.add(item);
    }

    public static Clue getClue(int id) {
        for (int i =0; i < clueList.size; i++){
            if (clueList.get(i).getId() == id){
                return clueList.get(i);
            }
        }
        throw new RuntimeException("Clue not in notebook");
    }

    public static Clue getClue(Clue clue) {
        int index = clueList.indexOf(clue, false);  //-1 when not found
        if (index >= 0){
            return clueList.get(index);
        }
        else{
            throw new RuntimeException("Clue not in notebook");
        }
    }

    public static Item getItem(int id){
        for (int i =0; i < itemList.size; i++){
            if (itemList.get(i).getId() == id){
                return itemList.get(i);
            }
        }
        throw new RuntimeException("Item not in notebook");
    }

    public static Item getItem(Item item) {
        int index = itemList.indexOf(item, false);  //-1 when not found
        if (index >= 0) {
            return itemList.get(index);
        }
        throw new RuntimeException("Item not in notebook");
    }

    public static Array<Clue> getClueList() {
        return clueList;
    }

    public static Array<Item> getItemList() {
        return itemList;
    }
}
