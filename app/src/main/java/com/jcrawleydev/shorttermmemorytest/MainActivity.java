package com.jcrawleydev.shorttermmemorytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcrawleydev.shorttermmemorytest.items.ItemLoader;
import com.jcrawleydev.shorttermmemorytest.items.ItemManager;
import com.jcrawleydev.shorttermmemorytest.states.GameState;
import com.jcrawleydev.shorttermmemorytest.tasks.StatusUpdate;
import com.jcrawleydev.shorttermmemorytest.tasks.SwitchTextTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextHolder, TaskRunner{


    private TextView textView;
    private Button beginTestButton;
    private boolean isTestRunning;
    private ItemManager itemManager;
    private GameState currentState;

    private View countdownLayout, displayWordsLayout, startLayout, resultsLayout, wordRecallLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.itemView);
        beginTestButton = findViewById(R.id.beginTestButton);

        countdownLayout = findViewById(R.id.countdown_layout);
        displayWordsLayout = findViewById(R.id.display_words_layout);
        startLayout = findViewById(R.id.start_layout);
        resultsLayout = findViewById(R.id.results_layout);
        wordRecallLayout = findViewById(R.id.word_recall_layout);


        beginTestButton.setOnClickListener(this);
        textView.setOnClickListener(this);
        itemManager = new ItemManager();
        ItemLoader itemLoader = new ItemLoader();
        itemLoader.loadItemsInto(itemManager);
        itemManager.initLists();
        itemManager.printWorkingList();
    }

    /*
        TODO
        - switch to text area after a round of words
        - fade words in and out
        - 5 second counter before next round
        - results of all rounds at the end, play again button
     */

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.beginTestButton){
            System.out.println("MainActivity, onClick() Is test running: " + isTestRunning);
            if(!isTestRunning){
                System.out.println("MainActivity onClick() entered test is not running block!");
                isTestRunning = true;
                startLayout.setVisibility(View.GONE);
                displayWordsLayout.setVisibility(View.VISIBLE);
                startTest();
                itemManager.resetForNextRound();

                System.out.println("MainActivity onClick() after invoking startTest()");
            }
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
       // nextItemFuture.cancel(false);
        System.out.println("Stopping task and setting textView visibility to gone!");
        System.out.flush();
        isTestRunning = false;
        //beginTestButton.setVisibility(View.VISIBLE);
        runOnUiThread(new Runnable(){
            public void run(){
                wordRecallLayout.setVisibility(View.VISIBLE);
                textView.setText("");
                displayWordsLayout.setVisibility(View.GONE);
            }
        });
    }


    public void switchToNextState(){
        runOnUiThread(new Runnable(){
            public void run(){
                currentState.stop();
                currentState = currentState.getNextState();
                currentState.start();
            }
        });

    }


    private void startTest(){
    }


}