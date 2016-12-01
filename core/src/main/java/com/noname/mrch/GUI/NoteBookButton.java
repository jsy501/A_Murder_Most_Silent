package com.noname.mrch.GUI;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by nxn1 on 01/12/2016.
 */
public class NoteBookButton extends Button {
    private Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
    private int xOrigin = 0;
    private int yOrigin = 0;
    private int xSize = 200;
    private int ySize = 200;

    public NoteBookButton(Drawable upRegion, Drawable downRegion) {
        this.buttonStyle.up = upRegion;
        this.buttonStyle.down = downRegion;
        this.setBounds(xOrigin,yOrigin,xSize,ySize);
        this.setStyle(buttonStyle);

    }

    public Button getButton(){
        return this;
    }
}

// in between these comments is stuff im testing out sorry james
// (when placed into game screen it displays a green button in
// the middle of the screen but i work out how to move or resize it.

    //Skin skin = new Skin();
    //Pixmap pixmap = new Pixmap(100,100, Pixmap.Format.RGBA8888);
    //    pixmap.setColor(Color.GREEN);
    //            pixmap.fill();
    //            skin.add("noteBookButton", new Texture(pixmap));
    //
    //            Drawable upRegion = skin.newDrawable("noteBookButton", Color.DARK_GRAY);
    //            Drawable downRegion = skin.newDrawable("noteBookButton", Color.DARK_GRAY);
    //
    //            NoteBookButton noteBookButton = new NoteBookButton(upRegion, downRegion);
    //            noteBookButton.setPosition(0,900);
    //
    //            Stage stage = new Stage();
    //            Table table = new Table();
    //            this.gui = new GUIWidgetGroup(stage, table);
    //
    //            gui.addWidget(noteBookButton);
// end of test

//add to renderer to make above test work
//        gui.getGuiStage().act();
//        gui.getGuiStage().draw();
