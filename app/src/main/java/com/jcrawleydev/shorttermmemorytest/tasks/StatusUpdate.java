package com.jcrawleydev.shorttermmemorytest.tasks;

public class StatusUpdate implements  Runnable{

    public void run(){
        System.out.println("Status update: changed text!");
    }
}
