package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 *  Represents a clue object
 *
 *  The clue object extends Game actor which is an aspect of libGDX
 *  doing this was a design choice to make using the clue class easier
 *  and less confusing as the alternative was for clue to contain an actor
 *
 *  The clue Object also implements the JsonImport class so that it can load
 *  all the clue objects from json at game startup.
 */

public class Clue extends GameActor implements JsonImport{
    public static final int ID_OFFSET = 100;
    /**
     * The ID_OFFSET variable is used in order to differentiate between
     * types of objects independent of everything else the id offset
     * is added to the id of the object imported via json
     */

    private int id;
    private String name;
    private String description;
    private ClueType clueType;
    private Array<Integer> relatedCharId = new Array<Integer>();

    /**
     * Use of each variable:
     * id contains the id of the object as imported via json with the ID_OFFSET value added
     * name contains the name of the object as displayed in game
     * description contains the description of the clue as displayed in game
     * clueType can be any value defined by the ClueType enum and is used to tell the game what type the clue is
     * relatedCharId contains the id's of all characters that any particular clue can point to mainly used for accusations
     */

    // TODO: 16/01/2017 clue type implementation

    private String response = "Clue response";

    /**
     * private string used as the generic clue response for a successful character questioning
     */


    public Clue(int id, String name, String description, ClueType clueType, Array<Integer> relatedCharId, String response){
        this.id = id;
        this.name = name;
        this.description = description;
        this.clueType = clueType;
        this.relatedCharId = relatedCharId;
        this.response = response;
    }



    public Clue(int id){
        this.id = id;
    }

    /**
     * The 2 constructors above are used for testing
     * only and are not used in any part of a normal game play experience
     */
    public Clue(){

    }

    /**
     * Constructor required for the json loader to function correctly
     */

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

    @Override
    public String toString() {
        return name;
    }

    public String getResponse() {
        return response;
    }

    public boolean checkId(GameCharacter target){
        if (this.getRelatedCharId().contains(target.getId(), true)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Simple function that checks whether the clue relates to the character
     * during an accusation. Function will be called during an accusation event for every
     * clue presented if at any point this function returns a false the accusation event will
     * fail. However even if no false value is returned the accusation event may still fail.
     */
    
}
