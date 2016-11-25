package com.noname.mrch.gameObject;

import com.noname.mrch.unused.Dialogue;

/**
 * Represents a character object
 */

public class Character extends HasObject {

	private int id;
	private String name;
	private int personality;
	private int role;
	private boolean isFalseAccused;
	private Dialogue dialogue;

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
