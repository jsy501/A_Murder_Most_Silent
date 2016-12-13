package com.noname.mrch.gameobject;

/**
 *  Represents an item object
 */

public class Item extends GameActor implements JsonImport{
    public static final int ID_OFFSET = 200;

    private int id;
    private String name;
    private String description;
    private int linkedPersonId;
    private Item returnItem;

    private boolean isKey = false;

//    private String returnResponse = "Thanks";

    public Item(){

    }

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String name, String description, boolean isKey){
        this.id = id;
        this.name = name;
        this.description = description;
        this.isKey = isKey;
    }

    public Item(int id, String name, String description, int linkedPersonId, Item returnItem, boolean isKey){
        this.id = id;
        this.name = name;
        this.description = description;
        this.isKey = isKey;
        this.linkedPersonId = linkedPersonId;
        this.returnItem = returnItem;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLinkedPersonId() {
        return linkedPersonId;
    }

    public Item getReturnItem() {
        return returnItem;
    }

    public void setReturnItem(Item returnItem) {
        this.returnItem = returnItem;
    }

    public boolean isKey() {
        return isKey;
    }

    @Override
    public String toString() {
        return name;
    }

    //    public String getReturnResponse() {
//        return returnResponse;
//    }
}
