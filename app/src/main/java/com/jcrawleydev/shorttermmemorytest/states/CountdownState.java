package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.states.manager.State;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.tasks.CountdownTask;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CountdownState extends AbstractGameState implements GameState {

    private StateManager stateManager;
    private int currentNumber;
    private final int INITIAL_NUMBER = 3;
    private ScheduledFuture<?> future;
    private ScheduledExecutorService scheduledExecutorService;
    private TextHolder textHolder;

    public CountdownState(StateManager stateManager, TextHolder textHolder){
        this.stateManager = stateManager;
        this.scheduledExecutorService = stateManager.getExecutorService();
        this.textHolder = textHolder;
    }

    @Override
    public void start() {
        currentNumber = INITIAL_NUMBER;
        CountdownTask task = new CountdownTask(textHolder, this);
        future = scheduledExecutorService.scheduleWithFixedDelay(task, 0,  1000, TimeUnit.MILLISECONDS);
    }


    @Override
    public void stop() {
        future.cancel(false);
        stateManager.switchTo(State.DISPLAY_WORDS);
    }


    @Override
    public void onClick(int id) {

    }


    public int getAndDecCurrentNumber(){
        int retVal = currentNumber;
        currentNumber--;
        if(currentNumber < 1){
            stop();
        }
        return retVal;
    }



}
