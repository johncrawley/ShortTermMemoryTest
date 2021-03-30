package com.jcrawleydev.shorttermmemorytest.states;

import android.view.View;

import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;

public abstract class AbstractGameState implements GameState {

    protected int layoutId;
    protected StateManager stateManager;
    protected StateName name;

    @Override
    public  int getLayoutId(){
        return layoutId;
    }

    @Override
    public void onKeyboardDone(String contents){

    }

    @Override
    public StateName getName(){
        return name;
    }

}
