package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gui.Gui;

/**
 * Holds items and clues found by the player
 */

public class NoteBook implements ObjectContainer{
    /**
     * Clue list holds all the collected clues
     * Item list holds all the collected items
     */
    private Array<Clue> clueList;
    private Array<Item> itemList;

    /**
     * Non-argument constructor used at initialisation of the notebook
     */
    public NoteBook(){
        clueList = new Array<>();
        itemList = new Array<>();
//
//        Item testItem1 = new Item(1, "item1", "", false);
//        Item testItem2 = new Item(1, "item2", "", false);
//        Item testItem3 = new Item(1, "item3", "", false);
//        Item testItem4 = new Item(1, "item4", "", false);
//        Item testItem5 = new Item(1, "item5", "", false);
//        Item testItem6 = new Item(1, "item6", "", false);
//        Item testItem7 = new Item(1, "item7", "", false);
//        Item testItem8 = new Item(1, "item8", "", false);
//        Item testItem9 = new Item(1, "item9", "", false);
//        Item testItem10 = new Item(1, "item10", "", false);
//
//        itemList.addAll(testItem1, testItem2, testItem3, testItem4, testItem5, testItem6, testItem7, testItem8, testItem9, testItem10);
    }

    /**
     * Adds the clue to the notebook throwing an exception if the clueList already
     * contains the clue
     * @param clue clue to be added to the notebook
     */
    @Override
    public void addClue(Clue clue){
        if (clueList.contains(clue, false)){
            throw new RuntimeException("Clue already in notebook");
        }
        clueList.add(clue);
    }

    /**
     * Adds the item to the notebook throwing an exception if the itemList already
     * contains the item
     * @param item item to be added to the notebook
     */
    @Override
    public void addItem(Item item){
        if (itemList.contains(item, false)){
            throw new RuntimeException("Item already in notebook");
        }
        itemList.add(item);
    }

    /**
     * searches through the clueList using the clue's unique id to find it
     * throws an exception if the clue cannot be found
     * @param id id of clue to be found
     * @return returns the clue once found
     */
    Clue getClue(int id) {
        for (int i =0; i < clueList.size; i++){
            if (clueList.get(i).getId() == id){
                return clueList.get(i);
            }
        }
        throw new RuntimeException("Clue not in notebook");
    }

    /**
     * searches through the itemList using the item's unique id to find it
     * throws an exception if the item cannot be found
     * @param id if of item to be found
     * @return returns the item once found
     */
    Item getItem(int id){
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

    @Override
    public void removeItem(Item item){
        itemList.removeValue(item, false);
    }

    @Override
    public void removeClue(Clue clue) {
        clueList.removeValue(clue, false);
    }

    @Override
    public Array<Clue> getClueList() {
        return clueList;
    }

    @Override
    public Array<Item> getItemList() {
        return itemList;
    }
}
