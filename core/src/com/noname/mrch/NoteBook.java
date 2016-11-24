package com.noname.mrch;

import com.badlogic.gdx.utils.Array;

public class NoteBook {
    private Array<Clue> clueList = new Array<Clue>();
    private Array<Item> itemList = new Array<Item>();

    public void addClue(Clue clue){
        clueList.add(clue);
    }

    public void addItem(Item item){
        itemList.add(item);
    }

    public Array<Clue> getClueList() {
        return clueList;
    }

    public Array<Item> getItemList() {
        return itemList;
    }
}
