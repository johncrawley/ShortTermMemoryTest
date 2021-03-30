package com.jcrawleydev.shorttermmemorytest.states;

import com.jcrawleydev.shorttermmemorytest.states.manager.StateName;

public interface GameState {

    void start();
    void stop();
    void onClick(int id);
    void onKeyboardDone(String contents);
    int getLayoutId();
    StateName getName();
}
