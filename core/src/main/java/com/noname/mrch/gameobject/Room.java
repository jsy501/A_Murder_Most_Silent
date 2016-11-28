package com.noname.mrch.gameobject;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Room extends HasObject {


	private int id;
	private String name;
	private boolean isLocked;

	private Stage stage = new Stage();
	
	public Room(int id, String name, boolean locked){
		this.id = id;
		this.name = name;
		this.isLocked = locked;
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
}
	
	
