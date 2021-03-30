package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;

public class StartState extends AbstractGameState implements GameState {

    private int startButtonId;
    private boolean isRunning = false;
    private StateManager stateManager;


    public StartState(StateManager stateManager){
        this.layoutId = R.id.start_layout;
        this.name = StateName.START;
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
            stateManager.switchTo(StateName.DISPLAY_WORDS);
        }
    }
}
