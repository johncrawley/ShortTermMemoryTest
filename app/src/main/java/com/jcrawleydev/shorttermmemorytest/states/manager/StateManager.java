package com.jcrawleydev.shorttermmemorytest.states.manager;

import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public interface StateManager {

    void switchTo(State state);
    void onClick(int viewId);
    void onKeyboardDone(String contents);
    ScheduledExecutorService getExecutorService();
}
