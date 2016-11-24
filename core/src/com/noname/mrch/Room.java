package com.noname.mrch;

import java.util.ArrayList;

public class Room {


	public int ID;
	public Boolean isLocked;
	public String name;
	public ArrayList<Item> itemList = new ArrayList<Item>() ;
	public ArrayList<Clue> clueList = new ArrayList<Clue>() ;
	
	public Room(int UID,  boolean islocked, String aName){
		
		ID = UID;
		isLocked = islocked;
		name = aName;
	}
	
	public void removeClue(Clue clue){
		this.clueList.remove(clue);
	}
	
	public void removeItem(Item item){
		this.itemList.remove(item);
	}
}
	
	
