package com.noname.mrch;

public class Character extends HasObject{


	private int personality, id, role;
	private Boolean isFalseAccused;
	private String name;

	
	private Character(int id, String name, int personality, int role, boolean falseAccused){
		this.personality = personality;
		this.id = id;
		this.role = role;
		this.isFalseAccused = falseAccused;
		this.name = name;
	}
}