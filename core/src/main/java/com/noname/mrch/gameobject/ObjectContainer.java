package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * Objects that give out clues and items
 */

public interface ObjectContainer {

	public void addItem(Item item);

	public void addClue(Clue clue);

	public Array<Item> getItemList();

	public Array<Clue> getClueList();

	public void removeClue(Clue clue);
	
	public void removeItem(Item item);
}
