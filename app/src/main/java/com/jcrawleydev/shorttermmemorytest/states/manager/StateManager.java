package com.jcrawleydev.shorttermmemorytest.states.manager;

import java.util.concurrent.ScheduledExecutorService;

public interface StateManager {

    void switchTo(StateName state);
    void onClick(int viewId);
    void onKeyboardDone(String contents);
    ScheduledExecutorService getExecutorService();
}
