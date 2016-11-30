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
    GameCharacter gameCharacter;

    public Texture getImage() {
        image = Gdx.files.getFileHandle(gameCharacter.getName()".txt");
        return image;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }
}
