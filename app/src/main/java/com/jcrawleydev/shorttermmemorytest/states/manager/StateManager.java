package com.jcrawleydev.shorttermmemorytest.states.manager;

import android.view.View;

public interface StateManager {

    void switchTo(State state);
    void onClick(int viewId);
    void onKeyboardDone(String contents);
}
