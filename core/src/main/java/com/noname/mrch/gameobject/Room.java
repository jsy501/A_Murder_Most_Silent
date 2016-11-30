package com.noname.mrch.gameobject;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Room implements JsonImport, ObjectContainer {

	private int id;
	private String name;
	private boolean isLocked;

	private Array<Item> itemList = new Array<Item>() ;
	private Array<Clue> clueList = new Array<Clue>() ;

	private Stage stage = new Stage();
	
	public Room(int id, String name, boolean locked){
		this.id = id;
		this.name = name;
		this.isLocked = locked;
	}

	public Room(int id, String name, boolean locked, Stage stage){
		this.id = id;
		this.name = name;
		this.isLocked = locked;
		this.stage = stage;
	}

	public Room(){

	}

	public void addActor(Actor actor){
		stage.addActor(actor);
	}

	public void removeActor(Actor actor){
		actor.remove();
	}

	public Stage getStage(){
		return stage;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void addItem(Item item) {
		itemList.add(item);
	}

	@Override
	public void addClue(Clue clue) {
		clueList.add(clue);
	}

	@Override
	public Array<Item> getItemList() {
		return itemList;
	}

	@Override
	public Array<Clue> getClueList() {
		return clueList;
	}

	@Override
	public void removeClue(Clue clue) {
		clueList.removeValue(clue, false);
	}

	@Override
	public void removeItem(Item item) {
		itemList.removeValue(item, false);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
	
	
