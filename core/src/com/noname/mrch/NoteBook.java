package com.noname.mrch;

import com.badlogic.gdx.utils.Array;

public class NoteBook {
    private static Array<Clue> clueList = new Array<Clue>();
    private static Array<Item> itemList = new Array<Item>();

    public static void addClue(Clue clue){
        clueList.add(clue);
    }

    public static void addItem(Item item){
        itemList.add(item);
    }

    public static Array<Clue> getClueList() {
        return clueList;
    }

    public static Array<Item> getItemList() {
        return itemList;
    }
}
