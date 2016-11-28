package com.noname.mrch.gameobject;

public class Room extends HasObject {


	private int id;
	private boolean isLocked;
	private String name;

	
	public Room(int id, String name, boolean locked){
		this.id = id;
		this.name = name;
		this.isLocked = locked;

	}
}
	
	
