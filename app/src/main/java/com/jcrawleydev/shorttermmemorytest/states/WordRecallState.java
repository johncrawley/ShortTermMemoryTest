package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.items.ItemCollector;
import com.jcrawleydev.shorttermmemorytest.states.manager.State;

public class WordRecallState extends AbstractGameState implements GameState {

    private int currentIteration;
    private int maxIterations;
    private ItemCollector itemCollector;
    private int doneButtonId;

    public WordRecallState(int maxIterations, ItemCollector itemCollector){
        this.layoutId = R.id.word_recall_layout;
        this.doneButtonId = R.id.doneButton;
        this.maxIterations = maxIterations;
        this.itemCollector = itemCollector;
    }



    @Override
    public void start() {
        currentIteration++;

    }


    @Override
    public void stop() {
        if(currentIteration == maxIterations){
            stateManager.switchTo(State.RESULTS);
        }
       stateManager.switchTo(State.COUNTDOWN);
    }


    @Override
    public void onClick(int id) {
        if(id == doneButtonId){
            stop();
        }

    }

    @Override
    public void onKeyboardDone(String contents){
        itemCollector.determineResultsFrom(contents);
    }

}
