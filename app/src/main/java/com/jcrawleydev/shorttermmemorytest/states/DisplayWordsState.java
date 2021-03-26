package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.R;
import com.jcrawleydev.shorttermmemorytest.TaskRunner;
import com.jcrawleydev.shorttermmemorytest.TextHolder;
import com.jcrawleydev.shorttermmemorytest.items.ItemManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.State;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.tasks.SwitchTextTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DisplayWordsState extends AbstractGameState implements GameState, TaskRunner {

    private ScheduledFuture<?> nextItemFuture;
    private ScheduledExecutorService scheduledExecutorService;
    private ItemManager itemManager;
    private TextHolder textHolder;


    public DisplayWordsState(ItemManager itemManager, StateManager stateManager, TextHolder textHolder){
        this.itemManager = itemManager;
        this.stateManager = stateManager;
        this.layoutId = R.id.display_words_layout;
        this.textHolder = textHolder;
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }



    @Override
    public void start(){
        final int TIME_PER_WORD = 2000;
        SwitchTextTask task = new SwitchTextTask(this, textHolder, itemManager);
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
        nextItemFuture.cancel(false);
        stateManager.switchTo(State.RECALL_WORDS);
    }



}
