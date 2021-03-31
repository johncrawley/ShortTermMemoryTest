package com.jcrawleydev.shorttermmemorytest.tasks;

import com.jcrawleydev.shorttermmemorytest.TaskRunner;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;
import com.jcrawleydev.shorttermmemorytest.items.ItemManager;


public class SwitchTextTask implements Runnable {

    private TextHolder textHolder;
    private ItemManager itemManager;
    private TaskRunner taskRunner;
    private int round;

    public SwitchTextTask(TaskRunner taskRunner, TextHolder textHolder, ItemManager itemManager, int round){
        this.textHolder = textHolder;
        this.itemManager = itemManager;
        this.taskRunner = taskRunner;
        this.round = round;
    }

    public void run(){
        if(itemManager.hasNext()){
            textHolder.setWordText(itemManager.getNextItem());
        }
        else{
            textHolder.setWordText("");
            taskRunner.stopTask();
        }
    }
}
