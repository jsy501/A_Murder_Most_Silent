package com.noname.mrch.gameObject;

/**
 * Represents a person object
 */

public class Person extends HasObject {

	private int id;
	private String name;
	private int personality;
	private boolean isMurderer = false;
	private boolean isVictim = false;
	private boolean isFalseAccused;
	private String greeting;
	private String response;

	public int getId(){
		return id;
	}

	public String getGreeting() {
		return greeting;
	}

	public String getResponse() {
		return response;
	}

	public int getPersonality() {
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

	public boolean isFalseAccused() {
		return isFalseAccused;
	}

	public void setFalseAccused(boolean falseAccused) {
		isFalseAccused = falseAccused;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
