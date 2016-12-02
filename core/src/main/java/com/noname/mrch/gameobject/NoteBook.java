package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * Holds items and clues found by the player
 */

public class NoteBook {
    private Array<Clue> clueList;
    private Array<Item> itemList;

    public NoteBook(){
        clueList = new Array<>();
        itemList = new Array<>();
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
