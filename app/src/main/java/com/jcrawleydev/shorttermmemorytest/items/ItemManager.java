package com.jcrawleydev.shorttermmemorytest.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ItemManager {

    private List<List<String>> itemLists;
    private final int NUMBER_OF_LISTS_TO_USE = 4;
    private final int NUMBER_OF_ITEMS_PER_LIST = 4;
    private List<String> workingList;
    private int currentIndex;
    private Set<String> chosenItemsSet;
    List<Integer> itemListIndexes = new ArrayList<>();


    public ItemManager(){
        itemLists = new ArrayList<>();
    }

    public void add(List<String> itemList){
        itemLists.add(itemList);
    }


    public void initLists(){
        int totalItems = NUMBER_OF_ITEMS_PER_LIST * NUMBER_OF_ITEMS_PER_LIST;
        workingList = new ArrayList<>(totalItems);
        chosenItemsSet = new HashSet<>(totalItems);
        System.out.println("ItemManager initLists() , about to generate Working list");
        generateWorkingList();
        System.out.println("ItemManager initLists() , generated Working list, size: "+  workingList.size());
        printWorkingList();
    }


    public String getNextItem(){
        log("Entered getNextItem()");
        if(currentIndex >= workingList.size()){
            log("getNextItem() currentIndex: " + currentIndex + " workList size: " + workingList.size());
            System.out.flush();
            currentIndex = 0;
        }
        String text = workingList.get(currentIndex);
        log("new string: " + text);
        currentIndex++;
        return text;
    }

    private void log(String msg){
        System.out.println("ItemManager "+ msg);
        System.out.flush();
    }


    public void printWorkingList(){
        StringBuilder str = new StringBuilder();
        for(String text: workingList){
            str.append(" ");
            str.append(text);
        }
        System.out.println("working list: " + str.toString());
    }

    public boolean hasNext(){
        System.out.println("ItemManager hasNext() currentIndex: " + currentIndex + " workingList size: " + workingList.size());
        System.out.flush();
        return currentIndex < workingList.size();
    }

    public void resetForNextRound() {
        currentIndex = 0;
    }


    private void generateWorkingList(){
        setItemListIndexes();

        for(int i =0; i < NUMBER_OF_ITEMS_PER_LIST; i++) {
            for(int j= 0; j < NUMBER_OF_LISTS_TO_USE; j++) {
                int listIndex = itemListIndexes.get(j);
                List<String> itemList = itemLists.get(listIndex);
                String item = getRandomItemFrom(itemList);
                workingList.add(item);
            }
        }

    }


    private String getRandomItemFrom(List<String> list){
        boolean result = false;
        String currentChoice = "";
        while(!result) {
            int index = ThreadLocalRandom.current().nextInt(list.size());
            currentChoice = list.get(index);
            result = this.chosenItemsSet.add(currentChoice);
        }
        return currentChoice;
    }


    private void setItemListIndexes(){
        if(itemLists.size() < NUMBER_OF_LISTS_TO_USE){
            throw new RuntimeException("Need to specify at least " + NUMBER_OF_LISTS_TO_USE + " lists, currently specified only: " + itemLists.size() );
        }

        itemListIndexes = new ArrayList<>();
        for(int i=0; i< itemLists.size(); i++){
            itemListIndexes.add(i);
        }
        Collections.shuffle(itemListIndexes);
    }

}
