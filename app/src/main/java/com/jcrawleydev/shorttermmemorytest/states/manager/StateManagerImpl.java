package com.jcrawleydev.shorttermmemorytest.states.manager;

import com.jcrawleydev.shorttermmemorytest.MainActivity;
import com.jcrawleydev.shorttermmemorytest.WordsList;
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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class StateManagerImpl implements StateManager {


    private final int NUMBER_OF_ROUNDS = 3;

    private Map<StateName, GameState> stateMap;
    private ItemManager itemManager;
    private ItemCollector itemCollector;
    private GameState currentState;
    private final MainActivity mainActivity;
    private final ScheduledExecutorService scheduledExecutorService;
    private WordsList wordsList;


    public StateManagerImpl(MainActivity mainActivity){
        setupItemStuff();
        scheduledExecutorService = Executors.newScheduledThreadPool(2);
        this.mainActivity = mainActivity;
        setupStateMap();
        wordsList = new WordsList(5);
    }


    private void setupItemStuff(){
        itemManager = new ItemManager();
        Results results = new Results(NUMBER_OF_ROUNDS);
        itemCollector = new ItemCollector(itemManager, results);
        ItemLoader itemLoader = new ItemLoader();
        itemLoader.loadItemsInto(itemManager);
        itemManager.initLists();
        itemManager.printWorkingList();
    }


    private void setupStateMap(){
        stateMap = new HashMap<>();
        stateMap.put(StateName.START, new StartState(this));
        stateMap.put(StateName.DISPLAY_WORDS, new DisplayWordsState(itemManager, this, mainActivity));
        stateMap.put(StateName.RECALL_WORDS,  new WordRecallState(NUMBER_OF_ROUNDS, itemCollector, this, mainActivity));
        stateMap.put(StateName.COUNTDOWN, new CountdownState(this, mainActivity));
        stateMap.put(StateName.RESULTS, new ResultsState(this, wordsList));
        initViews();
    }

    private void initViews(){
        for(GameState gameState : stateMap.values()){
            mainActivity.setVisibilityOnCurrentLayout(gameState, GONE);
        }
        currentState = stateMap.get(StateName.START);
        if(currentState == null){
            return;
        }
        mainActivity.setVisibilityOnCurrentLayout(currentState, VISIBLE);
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
