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
    private final ScheduledExecutorService scheduledExecutorService;
    private final ItemManager itemManager;
    private final TextHolder textHolder;
    private boolean wasStopCalled;
    private int round;


    public DisplayWordsState(ItemManager itemManager, StateManager stateManager, TextHolder textHolder){
        super(stateManager, StateName.DISPLAY_WORDS, R.id.display_words_layout);
        this.itemManager = itemManager;
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
        stateManager.switchTo(StateName.RECALL_WORDS);
        wasStopCalled = true;
    }


}
