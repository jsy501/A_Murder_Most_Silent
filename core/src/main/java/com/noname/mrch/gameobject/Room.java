package com.noname.mrch.gameobject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

	private boolean isDefault = true;
	private Stage defaultStage;
	private Stage investigateStage;


	public Room(int id, String name, boolean locked){
		this.id = id;
		this.name = name;
		this.isLocked = locked;
	}

	public Room(){
		defaultStage = new Stage(new ScreenViewport());
		investigateStage = new Stage(new ScreenViewport());
	}

	public void addCharacter(GameCharacter character){
		characterList.add(character);
		defaultStage.addActor(character);
	}


	public Array<GameCharacter> getCharacterList(){
		return characterList;
	}

	public void setDefaultBackground(TextureRegion background){
		Image image = new Image(background);
		image.setWidth(MRCH.GAME_WIDTH);
		image.setHeight(MRCH.GAME_HEIGHT);
		image.addListener(new ActorInputAdapter(image));
		defaultStage.addActor(image);
	}

	public void setInvestigateBackground(TextureRegion background){
		Image image = new Image(background);
		image.setWidth(MRCH.GAME_WIDTH);
		image.setHeight(MRCH.GAME_HEIGHT);
		image.addListener(new ActorInputAdapter(image));
		investigateStage.addActor(image);
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}

	public void switchCurrentStage(){
		isDefault = !isDefault;
	}

	public Stage getCurrentStage(){
		if (isDefault){
			return defaultStage;
		}
		else{
			return investigateStage;
		}
	}

	public void dispose(){
		defaultStage.dispose();
		investigateStage.dispose();
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void addItem(Item item) {
		itemList.add(item);
		investigateStage.addActor(item);
	}

	@Override
	public void addClue(Clue clue) {
		clueList.add(clue);
		investigateStage.addActor(clue);
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
	
	
