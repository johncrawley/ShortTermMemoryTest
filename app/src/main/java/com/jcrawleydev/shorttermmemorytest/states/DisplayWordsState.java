package com.jcrawleydev.shorttermmemorytest.states;

import android.text.Layout;
import android.view.View;

import com.jcrawleydev.shorttermmemorytest.MainActivity;
import com.jcrawleydev.shorttermmemorytest.TaskRunner;
import com.jcrawleydev.shorttermmemorytest.TextHolder;
import com.jcrawleydev.shorttermmemorytest.items.ItemManager;
import com.jcrawleydev.shorttermmemorytest.tasks.SwitchTextTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DisplayWordsState implements GameState, TaskRunner {

    private boolean isTestRunning = true;
    private ScheduledFuture<?> nextItemFuture;
    private ScheduledExecutorService scheduledExecutorService;
    private ItemManager itemManager;
    private TextHolder textHolder;
    private MainActivity mainActivity;
    private GameState nextState;
    private View layout;


    public DisplayWordsState(ItemManager itemManager, MainActivity mainActivity, View layout, GameState nextState){
        this.itemManager = itemManager;
        this.textHolder = mainActivity;
        this.mainActivity = mainActivity;
        this.layout = layout;
        this.nextState = nextState;

        scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }

    @Override
    public void start(){
        final int TIME_PER_WORD = 2000;
        isTestRunning = true;
        SwitchTextTask task = new SwitchTextTask(this, mainActivity, itemManager);
        nextItemFuture = scheduledExecutorService.scheduleWithFixedDelay(task, 100, TIME_PER_WORD, TimeUnit.MILLISECONDS);
    }


    @Override
    public void stopTask(){
        nextItemFuture.cancel(false);
        isTestRunning = false;
        //beginTestButton.setVisibility(View.VISIBLE);
        mainActivity.switchToNextState();
    }


    @Override
    public GameState getNextState(){
        return this.nextState;
    }


    public void stop(){
       // wordRecallLayout.setVisibility(View.VISIBLE);
       // textView.setText("");
        layout.setVisibility(View.GONE);
    }




}
