package com.noname.mrch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Room;
import com.noname.mrch.helper.AssetLoader;

import java.util.Stack;

/**
 *  Initialises and manages rooms
 *  Dependent on all of the other managers
 */

public class RoomManager {
    private Array<Room> roomArray; // all of the rooms EXCLUDING locked room
    private Room lockedRoom;
    private Stack<Room> roomStack = new Stack<>();

    RoomManager(AssetLoader assetLoader){
        roomArray = assetLoader.totalRoomArray;
        roomStack.push(roomArray.first()); //first room should always be hub

        // pick random room to be locked, excluding hub
        lockedRoom = roomArray.get(MathUtils.random(1,roomArray.size-1));
        lockedRoom.setLocked(true);

        roomArray.removeValue(lockedRoom, false);

        setBackground(assetLoader);
    }

    public void setBackground(AssetLoader assetLoader){
        TextureAtlas textureAtlas = assetLoader.manager.get("asset/graphics/room_pack.pack");
        for (Room room : roomArray){
            TextureRegion image = new TextureRegion(textureAtlas.findRegion(String.valueOf(room.getId())));
            room.setBackground(image);
        }
    }

    public Room getLockedRoom(){
        return lockedRoom;
    }

    public Array<Room> getRoomArray(){
        return roomArray;
    }

    public void setCurrentRoom(Room room){
        roomStack.pop();
        roomStack.push(room);
    }

    public Room getCurrentRoom(){
        return roomStack.peek();
    }
}
