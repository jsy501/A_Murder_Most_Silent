package com.noname.mrch.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.noname.mrch.MurderSilentGame;

public class Room implements JsonImport, ObjectContainer {
	public static final int ROOM_COUNT = 6;

	private int id;
	private String name;
	private String description;
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
		SpriteBatch batch = new SpriteBatch();
		defaultStage = new Stage(new FitViewport(MurderSilentGame.GAME_WIDTH, MurderSilentGame.GAME_HEIGHT), batch);
		investigateStage = new Stage(new FitViewport(MurderSilentGame.GAME_WIDTH, MurderSilentGame.GAME_HEIGHT), batch);
	}

	public void addCharacter(GameCharacter character){
		characterList.add(character);
		defaultStage.addActor(character);

		//this assumes that there is only one character in the room
		character.setPosition(MurderSilentGame.GAME_WIDTH / 2, 0, Align.bottom);
	}


	public Array<GameCharacter> getCharacterList(){
		return characterList;
	}

	public void setDefaultBackground(TextureRegion textureRegion){
		GameActor background = new GameActor();
		background.setImage(textureRegion);
		defaultStage.addActor(background);
	}

	public void setInvestigateBackground(TextureRegion textureRegion){
		GameActor background = new GameActor();
		background.setImage(textureRegion);
		investigateStage.addActor(background);
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean aDefault) {
		isDefault = aDefault;
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

	public void randomiseActorPos(){
		Array<GameActor> actorArray = new Array<>();
		actorArray.addAll(clueList);
		actorArray.addAll(itemList);

		int count = 0;
		while(count < actorArray.size){
			float horizontalPos = MathUtils.random(200, MurderSilentGame.GAME_WIDTH - 200);
			float verticalPos = MathUtils.random(200, MurderSilentGame.GAME_HEIGHT - 200);

			if (investigateStage.hit(horizontalPos, verticalPos, true) == null){
				actorArray.get(count).setPosition(horizontalPos, verticalPos);
				count++;
			}
		}
	}

	public void resize(int width, int height){
		defaultStage.getViewport().update(width, height, false);
		investigateStage.getViewport().update(width, height, false);
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

	@Override
	public String getDescription() {
		return description;
	}
}
	
	
