package com.jcrawleydev.shorttermmemorytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcrawleydev.shorttermmemorytest.states.GameState;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManagerImpl;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener, TextHolder {


    private TextView itemTextView;
    private StateManager stateManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button beginTestButton = findViewById(R.id.beginTestButton);
        Button doneButton = findViewById(R.id.doneButton);
        itemTextView = findViewById(R.id.itemView);
        EditText wordInputEditText = findViewById(R.id.editTextTextMultiLine);

        beginTestButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        itemTextView.setOnKeyListener(this);

        stateManager = new StateManagerImpl(this);
        setupKeyAction(wordInputEditText, MainActivity.this);



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
        stateManager.onClick(v.getId());
    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }


    @Override
    public void setWordText(final String text){
        runOnUiThread(new Runnable(){
            public void run(){
                itemTextView.setText(text);
            }
        });
    }


    @Override
    public void setCountdownText(final String value){
        runOnUiThread(new Runnable(){
            public void run(){
                TextView textView = findViewById(R.id.countdownText);
                textView.setText(value);
            }
        });
    }


    private void setupKeyAction(final EditText editText, final Context context){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(imm == null){
                        return false;
                    }
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    String contents = editText.getText().toString();
                    stateManager.onKeyboardDone(contents);
                    return true;
                }
                return false;
            }
        });
    }



    public void stopTask(final GameState currentState){
        runOnUiThread(new Runnable(){
            public void run(){
                currentState.stop();
                setVisibilityOnCurrentLayout(currentState, View.GONE);
            }
        });
    }


    public void startTask(final GameState currentState){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentState.start();
                setVisibilityOnCurrentLayout(currentState, View.VISIBLE);
            }
        });
    }



    private void setVisibilityOnCurrentLayout(GameState currentState, int visibility){
        findViewById(currentState.getLayoutId()).setVisibility(visibility);;
    }


}