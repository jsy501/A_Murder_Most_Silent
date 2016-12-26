package com.noname.mrch.gameobject;

import com.badlogic.gdx.utils.Array;

/**
 * Objects that give out clues and items
 */

public interface ObjectContainer {
	void addItem(Item item);
	void addClue(Clue clue);
	Array<Item> getItemList();
	Array<Clue> getClueList();
	void removeClue(Clue clue);
	void removeItem(Item item);
}
