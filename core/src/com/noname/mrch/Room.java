package com.noname.mrch;

public class Room extends HasObject {


	private int ID;
	private Boolean isLocked;
	private String name;

	
	public Room(int UID,  boolean islocked, String aName){
		
		ID = UID;
		isLocked = islocked;
		name = aName;
	}
}
	
	
