package com.jcrawleydev.shorttermmemorytest.items;

import java.util.Arrays;
import java.util.List;

public class ItemLoader {

    private List<String> items;
    private List<String> vechicleItems;
    private List<String> animalItems;
    private List<String> foodItems;

    public ItemLoader(){
        items = Arrays.asList("Fork", "Knife", "Spoon", "Plate", "Saucer", "Cup", "Bowl");
        vechicleItems = Arrays.asList("car", "bicycle", "train", "truck", "helicopter", "boat", "coach");
        animalItems = Arrays.asList("squirrel", "cat", "elephant", "goat", "mouse", "sheep", "giraffe", "rhino", "cow");
        foodItems = Arrays.asList("spinach", "tomato", "potato", "carrot", "celery", "lentil", "parsnip", "leek", "pea");
    }

    public void loadItemsInto(ItemManager itemManager){
        itemManager.add(items);
        itemManager.add(vechicleItems);
        itemManager.add(foodItems);
        itemManager.add(animalItems);
    }


}
