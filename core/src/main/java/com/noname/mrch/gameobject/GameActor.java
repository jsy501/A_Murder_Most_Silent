package com.noname.mrch.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.noname.mrch.GameWorld;
import com.noname.mrch.MRCH;

/**
 * Created by PPPPPP on 2016/11/30.
 */

public class GameActor extends Actor {
    private TextureRegion image;
    private boolean isTouched = false;

    public GameActor(){
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameWorld.touchedActor = (GameActor) event.getListenerActor();
                isTouched = true;
            }
        });
    }

    public void setImage(TextureRegion texture) {
        image = texture;
        float ratio = MRCH.GAME_HEIGHT / image.getRegionHeight();
        setWidth(image.getRegionWidth() * ratio);
        setHeight(image.getRegionHeight() * ratio);
        setOrigin(getWidth()/2, getHeight()/2);
        setPosition(MRCH.GAME_WIDTH / 2, 0, Align.bottom);
//        setBounds(0, 0, imageWidth, imageHeight);
    }

    public  TextureRegion getImage(){
        return image;
    }

    public void setTouched(boolean touched){
        isTouched = touched;
    }

    public boolean isTouched(){
        return isTouched;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (image != null) {
            batch.draw(image, getX(), getY(), getWidth(), getHeight());
        }
    }

}
