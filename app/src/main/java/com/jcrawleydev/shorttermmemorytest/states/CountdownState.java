package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;
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
    private boolean wasStopCalled;

    public CountdownState(StateManager stateManager, TextHolder textHolder){
        this.layoutId = R.id.countdown_layout;
        this.name = StateName.COUNTDOWN;
        this.stateManager = stateManager;
        this.scheduledExecutorService = stateManager.getExecutorService();
        this.textHolder = textHolder;

    }

    @Override
    public void start() {
        wasStopCalled = false;
        currentNumber = INITIAL_NUMBER;
        CountdownTask task = new CountdownTask(textHolder, this);
        future = scheduledExecutorService.scheduleWithFixedDelay(task, 0,  1000, TimeUnit.MILLISECONDS);
    }


    @Override
    public void stop() {
        if(wasStopCalled){
            return;
        }
        wasStopCalled = true;
        future.cancel(false);
        stateManager.switchTo(StateName.DISPLAY_WORDS);
    }


    @Override
    public void onClick(int id) {

    }


    public int getAndDecCurrentNumber(){
        int retVal = currentNumber;
        currentNumber--;
        if(currentNumber < 0){
            stop();
        }
        return retVal;
    }



}
