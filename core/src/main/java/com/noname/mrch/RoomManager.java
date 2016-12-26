package com.noname.mrch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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
 */

public class RoomManager {
    private Array<Room> roomArray; // all of the rooms EXCLUDING locked room
    private Room lockedRoom;

    RoomManager(AssetLoader assetLoader){
        roomArray = assetLoader.totalRoomArray;

        // pick random room to be locked, excluding hub
        lockedRoom = roomArray.get(MathUtils.random(1,roomArray.size-1));
        lockedRoom.setLocked(true);

        roomArray.removeValue(lockedRoom, false);

        setBackground(assetLoader);
    }

    private void setBackground(AssetLoader assetLoader){
        TextureAtlas textureAtlas = assetLoader.manager.get(assetLoader.roomTexturePath);

        //temporary background for investigate background
        Pixmap pixmap = new Pixmap(2,1, Pixmap.Format.RGBA4444);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        TextureRegion blank = new TextureRegion(new Texture(pixmap));

        for (Room room : roomArray){
            TextureRegion image = new TextureRegion(textureAtlas.findRegion(String.valueOf(room.getId())));
            room.setDefaultBackground(image);
            room.setInvestigateBackground(blank);
        }

        lockedRoom.setDefaultBackground(new TextureRegion(textureAtlas.findRegion(String.valueOf(lockedRoom.getId()))));
        lockedRoom.setInvestigateBackground(blank);
    }

    public Room getRoom(int id){
        if (lockedRoom.getId() == id){
            return lockedRoom;
        }

        for (Room room : roomArray){
            if (room.getId() == id){
                return room;
            }
        }

        throw new RuntimeException("Invalid id");
    }

    public Room getLockedRoom(){
        return lockedRoom;
    }

    public Array<Room> getRoomArray(){
        return roomArray;
    }

    public void dispose(){
        for (Room room: roomArray){
            room.dispose();
        }
        lockedRoom.dispose();
    }
}
