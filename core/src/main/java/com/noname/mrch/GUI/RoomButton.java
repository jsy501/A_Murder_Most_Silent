package com.noname.mrch.GUI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.noname.mrch.RoomManager;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.gameobject.Room;

/**
 * Created by PPPPPP on 2016/12/1.
 */
public class RoomButton extends Button {
    private TextButton rch;
    private TextButton room1;
    private TextButton lakehouse;
    private TextButton room2;
    private TextButton island;
    private TextButton room3;
    private Skin skin;
    private RoomManager rm;
    private Room room;
    private Player player;

    public RoomButton(Drawable upRegion, Drawable downRegion) {
        rch = new TextButton("Ron Cocke Hub",skin);
        room1 = new TextButton("Room1",skin);
        room2 = new TextButton("Room2",skin);
        room3 = new TextButton("Room3",skin);
        island = new TextButton("Island",skin);
        lakehouse = new TextButton("LakeHouse",skin);
        rch.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (room.isLocked()){
                    Dialog d = new Dialog("Room is locked",skin);
                }
                else{
                    //todo set player location
                }
            }
            //todo Code for all room buttons.
        });
    }
}
