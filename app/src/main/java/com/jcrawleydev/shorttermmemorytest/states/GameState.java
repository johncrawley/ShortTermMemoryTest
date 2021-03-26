package com.jcrawleydev.shorttermmemorytest.states;

public interface GameState {

    void start();
    void stop();
    void onClick(int id);
    void onKeyboardDone(String contents);
    int getLayoutId();
}
