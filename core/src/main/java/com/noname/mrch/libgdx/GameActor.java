package com.noname.mrch.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.noname.mrch.gameobject.GameCharacter;

/**
 * Created by PPPPPP on 2016/11/30.
 */
public class GameActor extends Actor {
    Texture image;
    String itemType;
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();


    public void setImage(Texture texture) {
        image = texture;
        float imageW = image.getWidth();
        float imageH = image.getHeight();
        float ratio = h / imageH;
        float iw = imageW * ratio;
        float ih = imageH * ratio;
        setBounds((w-iw)/2 , 0 , iw, ih );
    }

    public  Texture getImage(){
        return image;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,0,0);
    }

}
