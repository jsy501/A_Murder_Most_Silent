package com.noname.mrch.libgdx;

import com.badlogic.gdx.InputAdapter;

/**
 * Alternative to GameInputProcessor that only implements methods we need
 */
public class GameInputAdapter extends InputAdapter{
    @Override
    public boolean touchDown(int x, int y, int pointer, int button){
        //our touch down code here
        return true; //return true to indicate the event was handled
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button){
        //our touch up code here
        return true;
    }
}
