package com.noname.mrch.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.mrch.MRCH;
import com.noname.mrch.libgdx.ActorInputAdapter;
import com.noname.mrch.libgdx.GameStage;

public class Room implements JsonImport, ObjectContainer {
	public static final int ROOM_COUNT = 5;

	private int id;
	private String name;
	private boolean isLocked;

	private Array<GameCharacter> characterList = new Array<>();
	private Array<Item> itemList = new Array<Item>() ;
	private Array<Clue> clueList = new Array<Clue>() ;

	private Image background;

	private Array<GameStage> stages;


	public Room(int id, String name, boolean locked){
		this.id = id;
		this.name = name;
		this.isLocked = locked;
	}

	public Room(){
	}

	public void addCharacter(GameCharacter character){
		characterList.add(character);
		this.getCurrentStage().addActor(character);
	}


	public Array<GameCharacter> getCharacterList(){
		return characterList;
	}

	public void setBackground(Texture background){
		this.background = new Image(background);
		this.background.setWidth(MRCH.GAME_WIDTH);
		this.background.setHeight(MRCH.GAME_HEIGHT);
		this.background.addListener(new ActorInputAdapter(this.background));
		this.getCurrentStage().addActor(this.background);
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}

	public void setStages(GameStage mainStage, GameStage subStage){
		this.stages.add(mainStage);
		this.stages.add(subStage);
	}

	public void switchCurrentStage(){
		this.stages.swap(0,1);
	}

	public GameStage getCurrentStage(){
		return stages.get(0);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void addItem(Item item) {
		itemList.add(item);
		this.getCurrentStage().addActor(item);
	}

	@Override
	public void addClue(Clue clue) {
		clueList.add(clue);
		this.getCurrentStage().addActor(clue);
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
		clue.remove();
	}

	@Override
	public void removeItem(Item item) {
		itemList.removeValue(item, false);
		item.remove();
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
	
	
