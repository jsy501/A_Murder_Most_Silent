package com.noname.mrch.gameObject;

import com.noname.mrch.unused.Dialogue;

/**
 * Represents a person object
 */

public class Person extends HasObject {

	private int id;
	private String name;
	private int personality;
	private boolean isFalseAccused;
	private String greeting;

	public String getGreeting() {
		return greeting;
	}

	public String getResponse() {
		return response;
	}

	private String response;



	public int getPersonality() {
		return personality;
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
}
