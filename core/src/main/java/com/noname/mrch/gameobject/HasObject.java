package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * Objects that give out clues and items
 */

public class HasObject {
	private Array<Item> itemList = new Array<Item>() ;
	private Array<Clue> clueList = new Array<Clue>() ;

	public void addItem(Item item){
		itemList.add(item);
	}

	public void addClue(Clue clue){
		clueList.add(clue);
	}

	public Array<Item> getItemList() {
		return itemList;
	}

	public Array<Clue> getClueList() {
		return clueList;
	}

	public void removeClue(Clue clue){
		clueList.removeValue(clue, false);
	}
	
	public void removeItem(Item item){
		itemList.removeValue(item, false);
	}
}
