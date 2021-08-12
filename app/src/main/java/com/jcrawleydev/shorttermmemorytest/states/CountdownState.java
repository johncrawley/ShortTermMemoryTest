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

    private int currentNumber;
    private ScheduledFuture<?> future;
    private final ScheduledExecutorService scheduledExecutorService;
    private final TextHolder textHolder;
    private boolean wasStopCalled;


    public CountdownState(StateManager stateManager, TextHolder textHolder){
        super(stateManager, StateName.COUNTDOWN, R.id.countdown_layout);
        this.scheduledExecutorService = stateManager.getExecutorService();
        this.textHolder = textHolder;
    }


    @Override
    public void start() {
        final int INITIAL_NUMBER = 3;
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
