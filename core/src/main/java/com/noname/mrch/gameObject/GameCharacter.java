package com.noname.mrch.gameObject;

import com.noname.mrch.helper.Personality;

/**
 * Represents a gameCharacter object
 */

public class GameCharacter extends HasObject {
	public static final int ID_OFFSET = 300;
	public static final int ROSTER_NUMBER = 10;

	private int id;
	private String name;
	private Personality personality;
	private boolean isMurderer = false;
	private boolean isVictim = false;
	private boolean isAccused = false;
	private String greeting;
	private String negativeClueResponse;

	public int getId(){
		return id;
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

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
