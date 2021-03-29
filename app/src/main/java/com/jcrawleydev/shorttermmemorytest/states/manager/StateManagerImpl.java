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
    private Map<State, GameState> stateMap;
    private ItemManager itemManager;
    private ItemCollector itemCollector;
    private GameState currentState;
    private MainActivity mainActivity;
    private ScheduledExecutorService scheduledExecutorService;


    public StateManagerImpl(MainActivity mainActivity){

        setupStateMap();
        setupItemStuff();
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
        this.mainActivity = mainActivity;

        wordRecallState = new WordRecallState(5, itemCollector);
        displayWordsState = new DisplayWordsState(itemManager, this, mainActivity);
        startState = new StartState(this);
        countdownState = new CountdownState(this, mainActivity);
        resultState = new ResultsState(this);
        currentState = startState;
    }


    private void setupItemStuff(){
        itemManager = new ItemManager();
        results = new Results();
        itemCollector = new ItemCollector(itemManager, results);
        ItemLoader itemLoader = new ItemLoader();
        itemLoader.loadItemsInto(itemManager);
        itemManager.initLists();
        itemManager.printWorkingList();
    }


    private void setupStateMap(){

        stateMap = new HashMap<>();
        stateMap.put(State.START, startState);
        stateMap.put(State.DISPLAY_WORDS, displayWordsState);
        stateMap.put(State.RECALL_WORDS, wordRecallState);
        stateMap.put(State.COUNTDOWN, countdownState);
        stateMap.put(State.RESULTS, resultState);
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
    public void switchTo(State state) {
        mainActivity.stopTask(currentState);
        currentState = stateMap.get(state);
        mainActivity.startTask(currentState);
   }
}
