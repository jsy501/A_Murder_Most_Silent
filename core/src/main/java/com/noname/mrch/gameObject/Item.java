package com.noname.mrch.gameObject;

/**
 *  Represents an item object
 */

public class Item {
    private int id;
    private String description;
    private Person linkedPerson;
    private Item returnItem;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Person getLinkedPerson() {
        return linkedPerson;
    }

    public void setLinkedPerson(Person linkedPerson) {
        this.linkedPerson = linkedPerson;
    }

    public Item getReturnItem() {
        return returnItem;
    }

    public void setReturnItem(Item returnItem) {
        this.returnItem = returnItem;
    }
}
