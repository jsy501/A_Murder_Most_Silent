package com.noname.mrch;

import java.util.ArrayList;

public class HasObject {
	
	
	public ArrayList<Item> itemList = new ArrayList<Item>() ;
	public ArrayList<Clue> clueList = new ArrayList<Clue>() ;
	
	public void removeClue(Clue clue){
		this.clueList.remove(clue);
	}
	
	public void removeItem(Item item){
		this.itemList.remove(item);
	}
}
