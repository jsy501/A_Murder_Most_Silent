package com.noname.mrch;

public class Character extends HasObject{


	private int personality, id, role;
	private Boolean isFalseAccused;
	private String name;

	
	public Character(int id, String name, int personality, int role, boolean falseAccused){
		this.personality = personality;
		this.id = id;
		this.role = role;
		this.isFalseAccused = falseAccused;
		this.name = name;
	}

	public int getPersonality() {
		return personality;
	}

	public Boolean isFalseAccused() {
		return isFalseAccused;
	}

	public void setFalseAccused(Boolean falseAccused) {
		isFalseAccused = falseAccused;
	}

	public String getName() {
		return name;
	}
}