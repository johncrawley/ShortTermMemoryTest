package com.jcrawleydev.shorttermmemorytest.items;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private List<Integer> results;
    private List<List<String>> correctWords;
    private List<List<String>> incorrectWords;

    public Results(int numberOfRounds){
        results = new ArrayList<>();
        correctWords = new ArrayList<>(numberOfRounds);
        incorrectWords = new ArrayList<>(numberOfRounds);

        initializeWordResults(correctWords, numberOfRounds);
        initializeWordResults(incorrectWords, numberOfRounds);
    }

    private void initializeWordResults(List<List<String>> list, int numberOfRounds){
        for(int i =0; i< numberOfRounds; i++){
            list.add(new ArrayList<String>());
        }
    }

    public void add(int result){
        results.add(result);
    }

    public List<Integer> get(){
        return new ArrayList<>(results);
    }

}
