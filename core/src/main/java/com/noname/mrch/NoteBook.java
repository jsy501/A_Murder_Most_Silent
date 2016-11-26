package com.noname.mrch;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameObject.Clue;
import com.noname.mrch.gameObject.Item;

/**
 * Holds items and clues found by the player
 */

public class NoteBook {
    private static NoteBook INSTANCE = new NoteBook();

    private static Array<Clue> clueList = new Array<Clue>();
    private static Array<Item> itemList = new Array<Item>();

    private NoteBook(){
    }

    public static NoteBook getInstance(){
        return INSTANCE;
    }

    public static void addClue(Clue clue){
        if (clueList.contains(clue, false)){
            throw new RuntimeException("Clue already in notebook");
        }
        clueList.add(clue);
    }

    public static void addItem(Item item){
        if (itemList.contains(item, false)){
            throw new RuntimeException("Item already in notebook");
        }
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


    public static Item getItem(int id){
        for (int i =0; i < itemList.size; i++){
            if (itemList.get(i).getId() == id){
                return itemList.get(i);
            }
        }
        throw new RuntimeException("Item not in notebook");
    }

    public static boolean contains(Item item) {
        return (itemList.contains(item, false));
    }

    public static boolean contains(Clue clue) {
        return (clueList.contains(clue, false));
    }

    public static Array<Clue> getClueList() {
        return clueList;
    }

    public static Array<Item> getItemList() {
        return itemList;
    }
}
