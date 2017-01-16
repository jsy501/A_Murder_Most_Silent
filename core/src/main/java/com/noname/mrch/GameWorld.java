package com.noname.mrch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Array;
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

    public GameWorld(AssetLoader assetLoader) {
        player = new Player("John");
        notebook = new NoteBook();

        characterManager = new CharacterManager(assetLoader);
        itemManager = new ItemManager(assetLoader, characterManager);
        clueManager = new ClueManager(assetLoader, characterManager);
        roomManager = new RoomManager(assetLoader);

        distributeClue();
        distributeItem();
        distributeCharacter();

        //clues and items position randomisation
        for (Room room: roomManager.getRoomArray()) {
            room.randomiseActorPos();
        }
        roomManager.getLockedRoom().randomiseActorPos();


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

    private void distributeClue(){
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        Array<Room> roomArray = roomManager.getRoomArray();

        roomManager.getLockedRoom().addClue(clueManager.getWeaponClue());
        characterArray.random().addClue(clueManager.getMotiveClue());

        // array of potential clue holders
        Array<ObjectContainer> clueHolderArray = new Array<>();
        clueHolderArray.addAll(roomArray);
        clueHolderArray.addAll(characterArray);
        clueHolderArray.shuffle();

        // add every relevant appearance clue to a random clue holder
        for (int i = 0; i < clueManager.getAppearanceClueArray().size; i++){
            clueHolderArray.pop().addClue(clueManager.getAppearanceClueArray().get(i));
        }

        /*
        add irrelevant clues to every clue holder that doesn't have a clue
        the number of irrelevant clues should be less than or equal to half of relevant appearance
        clues + motive clue + weapon clue
        */
        int irrelevantClueCount = (clueManager.getAppearanceClueArray().size + 2) / 2;
        while(irrelevantClueCount > 0){
            clueHolderArray.pop().addClue(clueManager.getIrrelevantClueArray().pop());
            irrelevantClueCount--;
        }
    }

    private void distributeItem(){
        Array<Item> itemArray = itemManager.getItemArray();
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        Array<Room> roomArray = roomManager.getRoomArray();

        itemArray.add(itemManager.getKey());
        for (int i = 0; i < characterArray.size; i++) {
            //assign every item but the first one to every character
            //the first item is to be found in a room
            characterManager.getCharacterArray().get(i).addItem(itemManager.getItemArray().get(i+1));

            //create links between items
            itemManager.getItemArray().get(i).setReturnItem(itemManager.getItemArray().get(i+1));
        }
        itemArray.removeValue(itemManager.getKey(), false);

        //the first item is assigned to a random room
        roomArray.random().addItem(itemArray.get(0));
    }

    private void distributeCharacter(){
        Array<GameCharacter> characterArray = characterManager.getCharacterArray();
        Array<Room> roomArray = roomManager.getRoomArray();

        // character distribution
        characterArray.shuffle();
        int cnt = 0;
        Array.ArrayIterator<GameCharacter> iterator = new Array.ArrayIterator<>(characterArray);
        while(iterator.hasNext()){
            int roomIndex = cnt % (Room.ROOM_COUNT-1);
            if (!roomArray.get(roomIndex).isLocked()){
                roomArray.get(roomIndex).addCharacter(iterator.next());
            }
            cnt++;
        }
    }

    public void setGui(Gui gui){
        this.gui = gui;
    }

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

    public void setCurrentRoom(Room targetRoom){
        currentRoom.setLocked(false);
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

    public void dispose(){
        roomManager.dispose();
    }
}
