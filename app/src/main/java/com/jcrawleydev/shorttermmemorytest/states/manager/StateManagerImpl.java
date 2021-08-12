package com.jcrawleydev.shorttermmemorytest.states.manager;

import com.jcrawleydev.shorttermmemorytest.MainActivity;
import com.jcrawleydev.shorttermmemorytest.items.ItemCollector;
import com.jcrawleydev.shorttermmemorytest.items.ItemLoader;
import com.jcrawleydev.shorttermmemorytest.items.ItemManager;
import com.jcrawleydev.shorttermmemorytest.items.Results;
import com.jcrawleydev.shorttermmemorytest.states.CountdownState;
import com.jcrawleydev.shorttermmemorytest.states.DisplayWordsState;
import com.jcrawleydev.shorttermmemorytest.states.GameState;
import com.jcrawleydev.shorttermmemorytest.states.ResultsState;
import com.jcrawleydev.shorttermmemorytest.states.StartState;
import com.jcrawleydev.shorttermmemorytest.states.WordRecallState;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class StateManagerImpl implements StateManager {

    private GameState startState, displayWordsState, wordRecallState, countdownState, resultState;
    private Results results;
    private Map<StateName, GameState> stateMap;
    private ItemManager itemManager;
    private ItemCollector itemCollector;
    private GameState currentState;
    private MainActivity mainActivity;
    private ScheduledExecutorService scheduledExecutorService;


    public StateManagerImpl(MainActivity mainActivity){

        setupItemStuff();
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
        this.mainActivity = mainActivity;

        wordRecallState = new WordRecallState(5, itemCollector, this, mainActivity);
        displayWordsState = new DisplayWordsState(itemManager, this, mainActivity);
        startState = new StartState(this);
        countdownState = new CountdownState(this, mainActivity);
        resultState = new ResultsState(this);
        currentState = startState;

        setupStateMap();
    }


    private void setupItemStuff(){
        itemManager = new ItemManager();
        results = new Results(3);
        itemCollector = new ItemCollector(itemManager, results);
        ItemLoader itemLoader = new ItemLoader();
        itemLoader.loadItemsInto(itemManager);
        itemManager.initLists();
        itemManager.printWorkingList();
    }


    private void setupStateMap(){

        stateMap = new HashMap<>();
        stateMap.put(StateName.START, startState);
        stateMap.put(StateName.DISPLAY_WORDS, displayWordsState);
        stateMap.put(StateName.RECALL_WORDS, wordRecallState);
        stateMap.put(StateName.COUNTDOWN, countdownState);
        stateMap.put(StateName.RESULTS, resultState);
    }

    @Override
    public ScheduledExecutorService getExecutorService(){
        return this.scheduledExecutorService;
    }


    @Override
    public void onClick(int viewId){
        currentState.onClick(viewId);
    }

    @Override
    public void onKeyboardDone(String contents){
        currentState.onKeyboardDone(contents);
    }


    @Override
    public void switchTo(StateName stateName) {
        log("entering switchTo() with stateName: " + stateName.toString());
        mainActivity.stopTask(currentState);
        currentState = stateMap.get(stateName);
        if(currentState == null){
            log("switchTo() current state is null");
            return;
        }
        mainActivity.startTask(currentState);
   }


   private void log(String msg){
        System.out.println("StateMngrImpl: " + msg);
   }
}
