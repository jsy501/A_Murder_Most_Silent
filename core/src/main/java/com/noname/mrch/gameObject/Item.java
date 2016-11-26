package com.noname.mrch.gameObject;

/**
 *  Represents an item object
 */

public class Item {
    private int id;
    private String name;
    private String description;
    private int linkedPersonId;
    private Item returnItem;

    private boolean isKey;

//    private String returnResponse = "Thanks";

    public Item(){

    }

    public Item(int id, String name, String description, boolean isKey){
        this.id = id;
        this.name = name;
        this.description = description;
        this.isKey = isKey;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getLinkedPerson() {
        return linkedPersonId;
    }

    public void setLinkedPerson(int linkedPersonId) {
        this.linkedPersonId = linkedPersonId;
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
