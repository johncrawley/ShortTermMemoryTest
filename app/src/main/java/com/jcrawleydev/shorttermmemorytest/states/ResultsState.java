package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;

public class ResultsState extends AbstractGameState implements GameState{

    public ResultsState(StateManager stateManager){
        super(stateManager, StateName.RESULTS, R.id.results_layout);
    }


    @Override
    public void start() {
    }

    @Override
    public void stop() {

    }

    @Override
    public void onClick(int id) {

    }
}
