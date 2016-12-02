package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.MRCH;

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
	private Personality personality;
	private boolean isMurderer = false;
	private boolean isVictim = false;
	private boolean isAccused = false;
	private String greeting;
	private String negativeClueResponse;

	public GameCharacter(int id, String name, Personality personality, boolean isMurderer, boolean isVictim, boolean isAccused, String greeting, String negativeClueResponse){
		this.id = id;
		this.name = name;
		this.personality = personality;
		this.isMurderer = isMurderer;
		this.isVictim = isVictim;
		this.isAccused = isAccused;
		this.greeting = greeting;
		this.negativeClueResponse = negativeClueResponse;
	}

	public GameCharacter(){
	}

	public String getGreeting() {
		return greeting;
	}

	public String getResponse() {
		return negativeClueResponse;
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
