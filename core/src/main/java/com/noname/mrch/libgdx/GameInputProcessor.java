package com.noname.mrch.libgdx;

import com.badlogic.gdx.InputProcessor;
/**
 * Input event handling when an event is registered such as
 * left mouse button being pressed the method touchDown will
 * automatically be called.
 */
public class GameInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode)  {
        //Called when a key was pressed down. Reports the key code.
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        //Called when a key was lifted. Reports the key code.
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        //called when a unicode character was generated by keyboard input
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button){
        //Called when a finger went down on the screen or a mouse button was pressed. Reports the coordinates as well as the pointer index and mouse button
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //Called when a finger was lifted from the screen or a mouse button was released. Reports the last known coordinates as well as the pointer index and mouse button
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //Called when a finger is being dragged over the screen or the mouse is dragged while a button is pressed. Reports the coordinates and pointer index
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //Called when the mouse is moved over the screen without a mouse button being down. This event is only relevant on the desktop
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        //Called when the scroll wheel of the mouse was turned. Reports either -1 or 1 depending on the direction of spin.
        return false;
    }
}