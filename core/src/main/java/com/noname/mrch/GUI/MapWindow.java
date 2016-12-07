package com.noname.mrch.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gameobject.Room;

public class MapWindow extends GuiWindow{
    public MapWindow(Skin skin, GameWorld gameWorld) {
        super("MAP", skin, gameWorld);

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
        if (object.equals(true)){
            hide();
        }
        else if (object.equals(0)){
            gameWorld.moveRoom(400);
        }
        else if (object.equals(1)){
            gameWorld.moveRoom(401);
        }
        else if (object.equals(2)){
            gameWorld.moveRoom(402);
        }
        else if (object.equals(3)){
            gameWorld.moveRoom(403);
        }
        else if (object.equals(4)){
            gameWorld.moveRoom(404);
        }
        else{
            gameWorld.moveRoom(405);
        }
    }
}
