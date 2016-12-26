package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * Represents a gameCharacter object
 */

public class GameCharacter extends GameActor implements ObjectContainer, JsonImport {
	public static final int CHARACTER_COUNT = 6;
	public static final int ID_OFFSET = 300;

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
