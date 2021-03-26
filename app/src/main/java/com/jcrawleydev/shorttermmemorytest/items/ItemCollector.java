package com.jcrawleydev.shorttermmemorytest.items;

import java.util.Collections;
import java.util.List;

public class ItemCollector {

    private ItemManager itemManager;
    private Results results;

    public ItemCollector(ItemManager itemManager, Results results){
        this.itemManager = itemManager;
        this.results = results;
    }


    public void determineResultsFrom(String str){
        List<String> rememberedItems = parseItems(str);
        results.add(getNumberOfCorrectItems(rememberedItems));
    }


    private List<String> parseItems(String str){
        return Collections.emptyList();
    }


    private int getNumberOfCorrectItems(List<String> rememberedItems){
        int correctCount = 0;
        for(String item: rememberedItems){
            if(itemManager.contains(item)){
                correctCount++;
            }
        }
        return correctCount;
    }




}
