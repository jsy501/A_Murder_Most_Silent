package com.noname.mrch;

public class Character extends HasObject{


	private int personality, ID, role;
	private Boolean falseAccused;
	private String name;

	
	private Character(int aPersonality, int UID, int rolestate, boolean fAccused, String aName){
		personality = aPersonality;
		ID = UID;
		role = rolestate;
		falseAccused = fAccused;
		name = aName;	
	}

	public static void main(String args[]){

	}
}