package com.noname.mrch.gameObject;

/**
 *  Represents an item object
 */

public class Item {
    private int id;
    private String description;
    private int linkedPersonId;
    private Item returnItem;

    private boolean isKey;

//    private String returnResponse = "Thanks";

    public Item(){

    }

    public Item(int id, String description, boolean isKey){
        this.id = id;
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

//    public String getReturnResponse() {
//        return returnResponse;
//    }
}
