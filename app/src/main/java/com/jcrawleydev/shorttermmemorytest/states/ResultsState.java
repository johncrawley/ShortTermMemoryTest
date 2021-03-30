package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;

public class ResultsState extends AbstractGameState implements GameState{

    public ResultsState(StateManager stateManager){
        this.stateManager = stateManager;
    }

    @Override
    public void start() {
        this.layoutId = R.id.results_layout;
        this.name = StateName.RESULTS;
    }

    @Override
    public void stop() {

    }

    @Override
    public void onClick(int id) {

    }
}
