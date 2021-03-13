package com.jcrawleydev.shorttermmemorytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcrawleydev.shorttermmemorytest.items.ItemManager;
import com.jcrawleydev.shorttermmemorytest.tasks.StatusUpdate;
import com.jcrawleydev.shorttermmemorytest.tasks.SwitchTextTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextHolder, TaskRunner{


    private List<String> items;
    private List<String> vechicleItems;
    private List<String> animalItems;
    private List<String> foodItems;
    private TextView textView;
    private Button beginTestButton;
    private boolean isTestRunning;
    private ItemManager itemManager;
    private ScheduledFuture<?> nextItemFuture;

    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.itemView);
        beginTestButton = findViewById(R.id.beginTestButton);

        items = Arrays.asList("Fork", "Knife", "Spoon", "Plate", "Saucer", "Cup", "Bowl");
        vechicleItems = Arrays.asList("car", "bicycle", "train", "truck", "helicopter", "boat", "coach");
        animalItems = Arrays.asList("squirrel", "cat", "elephant", "goat", "mouse", "sheep", "giraffe", "rhino", "cow");
        foodItems = Arrays.asList("spinach", "tomato", "potato", "carrot", "celery", "lentil", "parsnip", "leek", "pea");
        beginTestButton.setOnClickListener(this);
        textView.setOnClickListener(this);
        itemManager = new ItemManager();
        itemManager.add(items);
        itemManager.add(vechicleItems);
        itemManager.add(foodItems);
        itemManager.add(animalItems);
        itemManager.initLists();

        scheduledExecutorService = Executors.newScheduledThreadPool(2);
        textView.setVisibility(View.GONE);
        itemManager.printWorkingList();
    }


    @Override
    public void onClick(View v){
        if(v.getId() == R.id.beginTestButton){
            System.out.println("MainActivity, onClick() Is test running: " + isTestRunning);
            if(!isTestRunning){
                System.out.println("MainActivity onClick() entered test is not running block!");
                isTestRunning = true;
                beginTestButton.setVisibility(View.GONE);
                textView.setText("test");
                textView.setVisibility(View.VISIBLE);
                startTest();
                itemManager.resetForNextRound();

                System.out.println("MainActivity onClick() after invoking startTest()");
            }
        }
        else if(v.getId() == R.id.itemView){

            beginTestButton.setVisibility(View.VISIBLE);
            isTestRunning = false;
            textView.setVisibility(View.GONE);
        }

    }


    @Override
    public void setText(final String text){
        runOnUiThread(new Runnable(){
            public void run(){
                textView.setText(text);
            }
        });
        System.out.println("Entered setText()");
        System.out.flush();
    }



    @Override
    public void stopTask(){
        nextItemFuture.cancel(false);
        isTestRunning = false;
        beginTestButton.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }



    private void startTest(){

        System.out.println("MainActivity onClick() entered startTest()");
        isTestRunning = true;
        SwitchTextTask task = new SwitchTextTask(this,this, itemManager);
        nextItemFuture = scheduledExecutorService.scheduleWithFixedDelay(task, 100, 1000, TimeUnit.MILLISECONDS);
    }


}