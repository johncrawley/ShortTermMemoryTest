package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.items.ItemCollector;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;

public class WordRecallState extends AbstractGameState implements GameState {

    private int currentIteration;
    private final int maxIterations;
    private ItemCollector itemCollector;
    private final int doneButtonId;
    private boolean wasStopCalled;
    private final TextHolder textHolder;

    public WordRecallState(int maxIterations, ItemCollector itemCollector, StateManager stateManager, TextHolder textHolder){
        super(stateManager, StateName.RECALL_WORDS, R.id.word_recall_layout);
        this.doneButtonId = R.id.doneButton;
        this.maxIterations = maxIterations;
        this.itemCollector = itemCollector;
        this.stateManager = stateManager;
        this.textHolder = textHolder;
    }


    @Override
    public void start() {
        wasStopCalled = false;
        currentIteration++;
    }


    @Override
    public void stop() {
        if(wasStopCalled){
            return;
        }

        wasStopCalled = true;
        if(currentIteration == maxIterations){
            stateManager.switchTo(StateName.RESULTS);
        }
        textHolder.clearRecallText();
        stateManager.switchTo(StateName.COUNTDOWN);
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
