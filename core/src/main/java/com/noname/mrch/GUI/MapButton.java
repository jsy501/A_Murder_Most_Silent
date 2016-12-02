package com.noname.mrch.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.noname.mrch.gameobject.Room;

/**
 * Mapbutton
 */
public class MapButton extends Button{
    private Button.ButtonStyle buttonStyle= new Button.ButtonStyle();

    public MapButton(Skin skin, ButtonStyle buttonStyle) {
        super(skin);
        this.buttonStyle = buttonStyle;
    }
    private Skin skin = new Skin();
    private Table table;
    private GUIWidgetGroup guistage;
    private RoomButton roombutton;
    final Dialog map = new Dialog("MAP",skin);

    public MapButton(Skin skin, ImageButton button) {

        addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            map.add(roombutton);//should show buttons for different rooms.
            map.align(Align.center);
            map.show(guistage.getGuiStage());
        }
    });
        table = new Table();
        table.setWidth(200);
        table.setHeight(200);
        table.align(Align.bottomRight);
        table.setPosition(0,0);
        table.add(button);
        guistage.addWidget(table);
    }


}

