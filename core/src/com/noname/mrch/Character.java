package com.noname.mrch;

import java.util.ArrayList;

public class Character {


	public int personality, ID, role;
	public Boolean falseAccused;
	public String name;
	public ArrayList<Item> itemList = new ArrayList<Item>() ;
	public ArrayList<Clue> clueList = new ArrayList<Clue>() ;
	
	public Character(int aPersonality, int UID, int rolestate, boolean fAccused, String aName){
		personality = aPersonality;
		ID = UID;
		role = rolestate;
		falseAccused = fAccused;
		name = aName;	
	}
	
	public void removeClue(Clue clue){
		this.clueList.remove(clue);
	}
	
	public void removeItem(Item item){
		this.itemList.remove(item);
	}

	public static void main(String args[]){
		
		Character Tunc = new Character(0, 333, 0, false, "Tunc") ;
		System.out.println(Tunc.ID);
		System.out.println(Tunc.itemList);	
	}
}