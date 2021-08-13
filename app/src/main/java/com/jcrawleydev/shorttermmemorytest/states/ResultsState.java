package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.WordsList;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;

public class ResultsState extends AbstractGameState implements GameState{

    private TextHolder textHolder;
    private WordsList wordsList;

    public ResultsState(StateManager stateManager, WordsList wordsList){
        super(stateManager, StateName.RESULTS, R.id.results_layout);
        this.wordsList = wordsList;
    }


    @Override
    public void start() {
        //checkWords();
    }

    @Override
    public void stop() {

    }

    @Override
    public void onClick(int id) {

    }
}
