package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * Represents a gameCharacter object
 */

public class GameCharacter extends GameActor implements ObjectContainer, JsonImport {
	/**
	 * The ID_OFFSET variable is used in order to differentiate between
	 * types of objects independent of everything else the id offset
	 * is added to the id of the object imported via json.
	 */
	public static final int ID_OFFSET = 300;

	/**
	 * Use of each variable:
	 * itemList contains a single item that the character will give to the player
     * clueList contains the clues a character may give when questioned
     * id contains the id of the object as imported via json with the ID_OFFSET value added
     * name contains the name of the object as displayed in game
     * description contains the description of the gameCharacter as given in game
     * personality can be any of the values defined by the personality enum and is used during questioning
     * isMurderer contains a boolean value that defines whether the character is the murderer or not
     * isVictim contains a boolean value that defines whether the character is the victim
     * isAccused contains a boolean value that defines whether the character has been falsely accused or not
     * greeting contains the generic greeting string as shown in game
     * questionFailResponse contains the generic questioning failure string as shown in game
	 */
	private Array<Item> itemList = new Array<Item>() ;
	private Array<Clue> clueList = new Array<Clue>() ;

	private int id;
	private String name;
	private String description;
	private Personality personality;
	private boolean isMurderer = false;
	private boolean isVictim = false;
	private boolean isAccused = false;
	private String greeting = "Greetings";
	private String questionFailResponse = "Question Fail";

    /**
     * Constructor used for testing only
     */
	public GameCharacter(int id, String name, Personality personality, boolean isMurderer, boolean isVictim, boolean isAccused, String greeting, String questionFailResponse){
		this.id = id;
		this.name = name;
		this.personality = personality;
		this.isMurderer = isMurderer;
		this.isVictim = isVictim;
		this.isAccused = isAccused;
		this.greeting = greeting;
		this.questionFailResponse = questionFailResponse;
	}

    /**
     * Non-argument constructor for json import
     */
	public GameCharacter(){
	}

	public String getGreeting() {
		return greeting;
	}

	public String getQuestionFailResponse() {
		return questionFailResponse;
	}

	public Personality getPersonality() {
		return personality;
	}

	public boolean isMurderer() {
		return isMurderer;
	}

	public void setMurderer(boolean murderer) {
		isMurderer = murderer;
	}

	public boolean isVictim() {
		return isVictim;
	}

	public void setVictim(boolean victim) {
		isVictim = victim;
	}

	public boolean isAccused() {
		return isAccused;
	}

	public void setAccused(boolean accused) {
		isAccused = accused;
	}

	@Override
	public int getId(){
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void addItem(Item item) {
		itemList.add(item);
	}

	@Override
	public void addClue(Clue clue) {
		clueList.add(clue);
	}

	@Override
	public Array<Item> getItemList() {
		return itemList;
	}

	@Override
	public Array<Clue> getClueList() {
		return clueList;
	}

	@Override
	public void removeClue(Clue clue) {
		clueList.removeValue(clue, false);
	}

	@Override
	public void removeItem(Item item) {
		itemList.removeValue(item, false);
	}
}
