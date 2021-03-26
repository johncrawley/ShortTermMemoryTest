package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.states.manager.State;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;

public class CountdownState extends AbstractGameState implements GameState {

    private StateManager stateManager;

    public CountdownState(StateManager stateManager){
        this.stateManager = stateManager;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        stateManager.switchTo(State.DISPLAY_WORDS);
    }

    @Override
    public void onClick(int id) {

    }
}
