package com.noname.mrch.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.noname.mrch.MRCH;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.libgdx.ActorInputAdapter;

/**
 * Created by PPPPPP on 2016/11/30.
 */

public class GameActor extends Actor {
    private TextureRegion image;

    public GameActor(){
        addListener(new ActorInputAdapter(this));
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (image != null) {
            batch.draw(image, getX(), getY(), getWidth(), getHeight());
        }
    }

}
