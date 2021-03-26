package com.jcrawleydev.shorttermmemorytest.items;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private List<Integer> results;


    public void add(int result){
        results.add(result);
    }

    public List<Integer> get(){
        return new ArrayList<>(results);
    }

}
