package com.jcrawleydev.shorttermmemorytest.states;

import android.view.View;

import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;

public abstract class AbstractGameState implements GameState {

    protected int layoutId;
    protected StateManager stateManager;

    @Override
    public  int getLayoutId(){
        return layoutId;
    }

    @Override
    public void onKeyboardDone(String contents){

    }

}
