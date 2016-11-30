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

    public Texture getImage(GameCharacter gameCharacter) {
        image = new Texture(Gdx.files.internal(gameCharacter.getName()+".png"));
        return image;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }
}
