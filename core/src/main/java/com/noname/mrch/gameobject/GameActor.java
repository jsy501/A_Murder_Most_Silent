package com.noname.mrch.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.mrch.GameWorld;
import com.noname.mrch.MurderSilentGame;

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
        float ratio = 1;
        if (this instanceof GameCharacter) {
            ratio = MurderSilentGame.GAME_HEIGHT / image.getRegionHeight();
        }
        setWidth(image.getRegionWidth() * ratio);
        setHeight(image.getRegionHeight() * ratio);
        setOrigin(getWidth()/2, getHeight()/2);
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
