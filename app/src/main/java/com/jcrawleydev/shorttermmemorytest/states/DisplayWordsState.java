package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.TaskRunner;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;
import com.jcrawleydev.shorttermmemorytest.items.ItemManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.tasks.SwitchTextTask;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DisplayWordsState extends AbstractGameState implements GameState, TaskRunner {

    private ScheduledFuture<?> nextItemFuture;
    private ScheduledExecutorService scheduledExecutorService;
    private ItemManager itemManager;
    private TextHolder textHolder;
    private boolean wasStopCalled;
    private int round;


    public DisplayWordsState(ItemManager itemManager, StateManager stateManager, TextHolder textHolder){
        this.itemManager = itemManager;
        this.stateManager = stateManager;
        this.layoutId = R.id.display_words_layout;
        this.name = StateName.DISPLAY_WORDS;
        this.textHolder = textHolder;
        scheduledExecutorService = stateManager.getExecutorService();
    }


    @Override
    public void start(){
        round++;
        wasStopCalled = false;
        final int TIME_PER_WORD = 4000;
        itemManager.resetForNextRound();
        SwitchTextTask task = new SwitchTextTask(this, textHolder, itemManager, round);
        nextItemFuture = scheduledExecutorService.scheduleWithFixedDelay(task, 100, TIME_PER_WORD, TimeUnit.MILLISECONDS);
    }


    @Override
    public void onClick(int id){
        //Do Nothing
    }

    @Override
    public void stopTask(){
        stop();
    }


    @Override
    public void stop(){
        if(wasStopCalled){
            return;
        }
        nextItemFuture.cancel(false);
        log("stop() nextItemFutureCancelled!");
        stateManager.switchTo(StateName.RECALL_WORDS);
        wasStopCalled = true;
    }

    private void log(String msg){
        System.out.println("DisplayWordsState: " + msg);
    }



}
