package com.noname.mrch.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.noname.mrch.MRCH;
import com.noname.mrch.gameobject.GameCharacter;

/**
 * Created by PPPPPP on 2016/11/30.
 */

public class GameActor extends Actor {
    TextureRegion image;

    public void setImage(TextureRegion texture) {
        image = texture;
        float ratio = MRCH.GAME_HEIGHT / image.getRegionHeight();
        float imageWidth = image.getRegionWidth() * ratio;
        float imageHeight = image.getRegionHeight() * ratio;
        setBounds(0, 0, imageWidth, imageHeight);
    }

    public  TextureRegion getImage(){
        return image;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(), getY(), getWidth(), getHeight());
    }

}
