package com.jcrawleydev.shorttermmemorytest.tasks;


import com.jcrawleydev.shorttermmemorytest.states.CountdownState;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;

public class CountdownTask implements Runnable {

    private TextHolder textHolder;
    private CountdownState countdownState;

    public CountdownTask(TextHolder textHolder, CountdownState countdownState){
        this.textHolder = textHolder;
        this.countdownState = countdownState;
    }

    public void run(){
        textHolder.setCountdownText(String.valueOf(countdownState.getAndDecCurrentNumber()));
    }
}
