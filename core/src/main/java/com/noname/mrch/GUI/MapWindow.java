package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gameobject.Room;

public class MapWindow extends GuiWindow{
    public MapWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("MAP", skin, gui, gameWorld);

        button("HUB", 0);
        button("ISLAND", 1);
        button("LECTURE HALL", 2);
        button("SEMINAR ROOM", 3);
        button("STUDY ROOM", 4);
        button("TUTOR ROOM", 5);

        getButtonTable().row().colspan(Room.ROOM_COUNT);
        button("CANCEL", true);
    }

    @Override
    protected void result(Object object) {
        int roomId = 0;

        if (object.equals(true)){
            hide();
        }
        else if (object.equals(0)){
            roomId = 400;
        }
        else if (object.equals(1)){
            roomId = 401;
        }
        else if (object.equals(2)){
            roomId = 402;
        }
        else if (object.equals(3)){
            roomId = 403;
        }
        else if (object.equals(4)){
            roomId = 404;
        }
        else{
            roomId = 405;
        }

        //if the room is locked and the move fails
        if (!gameWorld.moveRoom(roomId)){
            gui.displayInfo(null, "The room is locked");
        }
    }
}
