package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;

public abstract class AbstractGameState implements GameState {

    protected int layoutId;
    protected StateManager stateManager;
    protected StateName stateName;

    public AbstractGameState(StateManager stateManager, StateName stateName, int layoutId){
        this.stateManager = stateManager;
        this.stateName = stateName;
        this.layoutId = layoutId;
    }

    @Override
    public  int getLayoutId(){
        return layoutId;
    }

    @Override
    public void onKeyboardDone(String contents){

    }

    @Override
    public StateName getStateName(){
        return stateName;
    }

}
