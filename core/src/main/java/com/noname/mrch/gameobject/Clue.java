package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 *  Represents a clue object.
 */

public class Clue extends GameActor implements JsonImport, Scalable{
    /**
     * The ID_OFFSET variable is used in order to differentiate between
     * types of objects independent of everything else the id offset
     * is added to the id of the object imported via json.
     */
    public static final int ID_OFFSET = 100;


    /**
     * Use of each variable:
     * id contains the id of the object as imported via json with the ID_OFFSET value added
     * name contains the name of the object as displayed in game
     * description contains the description of the clue as displayed in game
     * relatedCharId contains the id's of all characters that any particular clue can point to mainly used for accusations
     * investigateScaleFactor is the scale it is rendered when rendered in investigate screen
     */
    private int id;
    private String name;
    private String description;
    private ClueType clueType;
    private ClueTag clueTag;
    private Array<Integer> relatedCharId = new Array<Integer>();

    // default is 1 unless specified in json
    private float investigateScaleFactor = 1;

    /**
     *  Used as the generic clue response for a successful character questioning
     */
    private String response = "Clue response";



    /**
     * Constructor for testing only
     */
    public Clue(int id, String name, String description, ClueType clueType, ClueTag clueTag, Array<Integer> relatedCharId, String response){
        this.id = id;
        this.name = name;
        this.description = description;
        this.clueType = clueType;
        this.clueTag = clueTag;
        this.relatedCharId = relatedCharId;
        this.response = response;
    }

    /**
     * Constructor for testing only
     */
    public Clue(int id){
        this.id = id;
    }


    /**
     * Non-argument constructor for json import
     */
    public Clue(){

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

    public Array<Integer> getRelatedCharId() {
        return relatedCharId;
    }

    public ClueType getClueType() {
        return clueType;
    }

    public ClueTag getClueTag() {
        return clueTag;
    }

    @Override
    public String toString() {
        return name;
    }

    String getResponse() {
        return response;
    }

    /**
     * Check if the target is related to the clue.
     * @param target Game character to be checked.
     * @return Returns true if the target is related.
     */
    boolean checkId(GameCharacter target){
        if (this.getRelatedCharId().contains(target.getId(), true)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public float getInvestigateScaleFactor() {
        return investigateScaleFactor;
    }
}
