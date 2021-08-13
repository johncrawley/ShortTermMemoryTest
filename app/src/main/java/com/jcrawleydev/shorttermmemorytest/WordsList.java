package com.jcrawleydev.shorttermmemorytest;

import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;

public class WordsList {

    private final int MAX_WORDS;
    private List<String> words;


    public WordsList(int maxWords){
        MAX_WORDS = maxWords;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setWords(String text){

        String[] wordsArray = text.split(" ", MAX_WORDS);
        for(String word : wordsArray){

        }
        words = Arrays.stream(wordsArray).map(String::toLowerCase).map(String::trim).collect(Collectors.toList());
    }

    public List<String> getWords(){
        return new ArrayList<>(words);
    }
}
