package com.noname.mrch.gameobject;

/**
 *  Represents an item object
 */

public class Item extends GameActor implements JsonImport, Scalable{
    /**
     * The ID_OFFSET variable is used in order to differentiate between
     * types of objects independent of everything else the id offset
     * is added to the id of the object imported via json.
     */
    public static final int ID_OFFSET = 200;

    /**
     * Use of each variable:
     * id contains the id of the object as imported via json with the ID_OFFSET value added
     * name contains the name of the object as displayed in game
     * description contains the description of the object as shown in game
     * linkedPersonId is the id value of the character that when given the item will return a new item or a key
     * returnItem is the item object that will be returned after a successful give interaction
     * investigateScaleFactor is the scale it is rendered when rendered in investigate screen
     * isKey is a boolean value that defines whether the item is a key or not
     */
    private int id;
    private String name;
    private String description;
    private int linkedPersonId;
    private Item returnItem;

    private float investigateScaleFactor = 1;

    private boolean isKey = false;

//    private String returnResponse = "Thanks";

    /**
     * Non-argument constructor for json import
     */
    public Item(){

    }

    /**
     * Constructor for testing only
     */
    public Item(int id) {
        this.id = id;
    }

    /**
     * Constructor for testing only
     */
    public Item(int id, String name, String description, boolean isKey){
        this.id = id;
        this.name = name;
        this.description = description;
        this.isKey = isKey;
    }

    /**
     * Constructor for testing only
     */
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

    @Override
    public float getInvestigateScaleFactor() {
        return investigateScaleFactor;
    }
}
