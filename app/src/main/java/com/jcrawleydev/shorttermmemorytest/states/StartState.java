package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.states.manager.State;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;

public class StartState extends AbstractGameState implements GameState {

    private int startButtonId;
    private boolean isRunning = false;
    private StateManager stateManager;


    public StartState(StateManager stateManager){
        this.layoutId = R.id.start_layout;
        this.startButtonId = R.id.beginTestButton;
        this.stateManager = stateManager;
    }


    @Override
    public void start(){
        isRunning = false;
    }

    @Override
    public void stop() {
        //do nothing
    }


    @Override
    public void onClick(int id) {
        if(id == startButtonId){
            if(isRunning){
                return;
            }
            isRunning = true;
            stateManager.switchTo(State.DISPLAY_WORDS);
        }
    }
}
