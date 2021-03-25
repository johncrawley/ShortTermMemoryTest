package com.jcrawleydev.shorttermmemorytest.states;

public interface GameState {

    void start();
    void stop();
    GameState getNextState();

}
