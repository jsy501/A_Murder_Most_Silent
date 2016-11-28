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

    public void addClue(Clue clue){
        if (clueList.contains(clue, false)){
            throw new RuntimeException("Clue already in notebook");
        }
        clueList.add(clue);
    }

    public void addItem(Item item){
        if (itemList.contains(item, false)){
            throw new RuntimeException("Item already in notebook");
        }
        itemList.add(item);
    }

    public Clue getClue(int id) {
        for (int i =0; i < clueList.size; i++){
            if (clueList.get(i).getId() == id){
                return clueList.get(i);
            }
        }
        throw new RuntimeException("Clue not in notebook");
    }


    public Item getItem(int id){
        for (int i =0; i < itemList.size; i++){
            if (itemList.get(i).getId() == id){
                return itemList.get(i);
            }
        }
        throw new RuntimeException("Item not in notebook");
    }

    public boolean contains(Item item) {
        return (itemList.contains(item, false));
    }

    public boolean contains(Clue clue) {
        return (clueList.contains(clue, false));
    }

    public Array<Clue> getClueList() {
        return clueList;
    }

    public Array<Item> getItemList() {
        return itemList;
    }
}
