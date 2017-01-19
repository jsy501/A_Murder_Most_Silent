package com.noname.mrch.gameobject;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.mrch.GameWorld;
import com.noname.mrch.MurderSilentGame;

/**
 * GameActor is a class that is used by graphical items
 * it handles rendering for the object and then the data
 * classes extend this.
 */

public class GameActor extends Actor {
    /**
     * isTouched is used for checking whether
     * the actor has been clicked on by the player
     */
    private TextureRegion image;
    private boolean isTouched = false;

    /**
     * Constructor method that sets up the event listener
     * for the actor and defines what happens when the actor
     * is clicked on
     */
    public GameActor(){
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameWorld.touchedActor = (GameActor) event.getListenerActor();
                isTouched = true;
            }
        });
    }

    /**
     * Set's the texture for the actor
     * @param texture texture for graphical representation of object
     */
    public void setImage(TextureRegion texture) {
        image = texture;
        float ratio = 1;

        //characters have to occupy full height of the screen, so scale according to the screen size
        if (this instanceof GameCharacter) {
            ratio = MurderSilentGame.GAME_HEIGHT / image.getRegionHeight();
        }

        setWidth(image.getRegionWidth() * ratio);
        setHeight(image.getRegionHeight() * ratio);
        setOrigin(getWidth()/2, getHeight()/2);
    }

    public void setTouched(boolean touched){
        isTouched = touched;
    }

    public boolean isTouched(){
        return isTouched;
    }

    /**
     * Checks if there is an image assigned and draw if there is
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (image != null) {
            batch.draw(image, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

}
