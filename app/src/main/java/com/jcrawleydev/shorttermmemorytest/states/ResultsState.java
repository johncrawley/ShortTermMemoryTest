package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.WordsList;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;

public class ResultsState extends AbstractGameState implements GameState{

    private TextHolder textHolder;
    private WordsList wordsList;
    private boolean running;
    private final int mainMenuButtonId;

    public ResultsState(StateManager stateManager, WordsList wordsList){
        super(stateManager, StateName.RESULTS, R.id.results_layout);
        this.wordsList = wordsList;
        mainMenuButtonId = R.id.resultsMainMenuButton;
    }


    @Override
    public void start() {
        running = true;
        //checkWords();
    }

    @Override
    public void stop() {
        running = false;
    }


    @Override
    public void onClick(int id) {
        if(id == mainMenuButtonId && running){
            stop();
            stateManager.switchTo(StateName.DISPLAY_WORDS);
        }
    }
}
