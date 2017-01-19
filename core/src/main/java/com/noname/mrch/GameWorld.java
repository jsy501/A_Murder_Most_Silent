package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.ClueTag;
import com.noname.mrch.gameobject.ObjectContainer;
import com.noname.mrch.gui.Gui;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.GameActor;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.NoteBook;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;
import com.noname.mrch.helper.Randomizer;

/**
 * Initialises and holds all the game objects
 * Handles inputs for in-game objects such as characters, items and clues
 */

public class GameWorld {
    public static GameActor touchedActor = null;

    private Player player;
    private NoteBook notebook;

    private Room currentRoom;
    private Gui gui;

    private CharacterManager characterManager;
    private ItemManager itemManager;
    private ClueManager clueManager;
    private RoomManager roomManager;

    public GameWorld(Player player, AssetLoader assetLoader) {
        this.player = player;
        notebook = new NoteBook();

        characterManager = new CharacterManager(assetLoader);
        itemManager = new ItemManager(assetLoader, characterManager);
        clueManager = new ClueManager(assetLoader, characterManager);
        roomManager = new RoomManager(assetLoader);

        Randomizer.randomDistributeGameObject(clueManager, itemManager, characterManager, roomManager);

        System.out.println("Character list: " + characterManager.getCharacterArray());
        System.out.println("Murderer: " + characterManager.getMurderer());
        System.out.println("Victim: " + characterManager.getVictim());
        System.out.println("Item list: " + itemManager.getItemArray());
        System.out.println("MOTIVE Clue: " + clueManager.getMotiveClue());
        System.out.println("WEAPON Clue: " + clueManager.getWeaponClue());
        System.out.println("APPEARANCE Clues: " + clueManager.getAppearanceClueArray());
        System.out.println("Room list: " + roomManager.getRoomArray());

        currentRoom = roomManager.getRoomArray().first();
    }

    /**
     * Attach gui to game world.
     * @param gui gui to attach
     */
    public void setGui(Gui gui){
        this.gui = gui;
    }

    /**
     * Check for input every frame.
     */
    public void update(float delta){
        // input check
        if (touchedActor != null) {
            Array actorArray = currentRoom.getCurrentStage().getActors();
            for (int i = 0; i < actorArray.size; i++) {
                GameActor actor = (GameActor) actorArray.get(i);
                if (actor.isTouched()) {
                    actor.setTouched(false);
                    handleInput(actor);
                }
            }
        }
    }

    private void handleInput(GameActor actor) {
        if (actor instanceof Clue) {
            Clue clue = (Clue) actor;
            notebook.addClue(clue);
            currentRoom.removeClue(clue);

            gui.displayInfo(actor, clue.getDescription());

            System.out.println("Clue clicked");

        } else if (actor instanceof Item) {
            Item item = (Item) actor;
            notebook.addItem(item);
            currentRoom.removeItem(item);

            gui.displayInfo(item, item.getDescription());

            System.out.println("Item clicked");

        } else if (actor instanceof GameCharacter) {
            gui.startInteraction((GameCharacter)actor);

            System.out.println("Character clicked");

        } else{
            System.out.println("Background clicked");
        }
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

    private void setCurrentRoom(Room targetRoom){
        currentRoom.setDefault(true);
        gui.haltInteraction();

        currentRoom = targetRoom;
        Gdx.input.setInputProcessor(new InputMultiplexer(gui.getStage(), currentRoom.getCurrentStage()));
    }

    /**
     * Move to the target room
     * @param id room id
     * @return true if successful, false if unsuccessful
     */

    public boolean moveRoom(int id){
        Room targetRoom = roomManager.getRoom(id);
        if (!targetRoom.isLocked()){
            setCurrentRoom(targetRoom);
            return true;
        }
        // if the room is locked and the player had found the key
        else if (targetRoom.isLocked() && notebook.contains(itemManager.getKey())){
            targetRoom.setLocked(false);
            setCurrentRoom(targetRoom);
            return true;
        }
        else{
            return false;
        }
    }

    public void resize(int width, int height){
        for (Room room : roomManager.getRoomArray()) {
            room.resize(width, height);
        }
        roomManager.getLockedRoom().resize(width, height);
    }

    public Player getPlayer() {
        return player;
    }

    public NoteBook getNotebook() {
        return notebook;
    }

    public ClueManager getClueManager() {
        return clueManager;
    }

    public RoomManager getRoomManager(){
        return roomManager;
    }

    public void dispose(){
        roomManager.dispose();
    }
}
